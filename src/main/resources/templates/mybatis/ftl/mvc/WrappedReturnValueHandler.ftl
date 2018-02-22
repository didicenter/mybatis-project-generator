package ${packageName}.configuration.mvc;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.thoughtworks.xstream.XStream;
import ${packageName}.configuration.fastjson.FastJsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class WrappedReturnValueHandler implements HandlerMethodReturnValueHandler {

    private static final String XML_FORMAT = "xml";
    private static final String JSON_FORMAT = "json";
    private static final String ALL_VALUE = "*/*";

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Wrapped wrapResult = returnType.getMethodAnnotation(Wrapped.class);
        return Objects.nonNull(wrapResult);
    }

    @Override
    public void handleReturnValue(Object obj, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {

        RequestMapping requestMapping = methodParameter.getMethodAnnotation(RequestMapping.class);
        GetMapping getMapping = methodParameter.getMethodAnnotation(GetMapping.class);
        PostMapping postMapping = methodParameter.getMethodAnnotation(PostMapping.class);
        PutMapping putMapping = methodParameter.getMethodAnnotation(PutMapping.class);
        DeleteMapping deleteMapping = methodParameter.getMethodAnnotation(DeleteMapping.class);
        PatchMapping patchMapping = methodParameter.getMethodAnnotation(PatchMapping.class);

        String[] produces = null;
        if (Objects.nonNull(requestMapping)) {
            produces = requestMapping.produces();
        } else if (Objects.nonNull(getMapping)) {
            produces = getMapping.produces();
        } else if (Objects.nonNull(postMapping)) {
            produces = postMapping.produces();
        } else if (Objects.nonNull(putMapping)) {
            produces = putMapping.produces();
        } else if (Objects.nonNull(deleteMapping)) {
            produces = deleteMapping.produces();
        } else if (Objects.nonNull(patchMapping)) {
            produces = patchMapping.produces();
        }
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (Objects.nonNull(produces)) {
            List<String> produceList = Arrays.asList(produces);
            produceList.stream().filter(StringUtils::isNotBlank).forEach(item -> response.setContentType(item));
        }
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String clientAccept = request.getHeader("Accept");
        if (StringUtils.isNotBlank(clientAccept) && (!ALL_VALUE.equals(clientAccept))) {
            response.setContentType(clientAccept);
        }
        ResultData resultData = new ResultData();
        resultData.setData(obj);
        if (PlainValue.class.isInstance(obj)) {
            PlainValue text = (PlainValue) obj;
            resultData.setData(text.getValue());
        }
        try (PrintWriter writer = response.getWriter()) {
            String responseFormat = response.getContentType();
            if (Objects.nonNull(responseFormat)) {
                responseFormat = responseFormat.toLowerCase();
                if (responseFormat.contains(XML_FORMAT)) {
                    XStream xStream = XStreamUtils.customerXStream();
                    writer.write(xStream.toXML(resultData));
                }
                if (responseFormat.contains(JSON_FORMAT)) {
                    FastJsonConfig fastJsonConfig = FastJsonUtils.defaultConfig();
                    writer.write(JSONObject.toJSONString(resultData, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializerFeatures()));
                }
            } else {
                FastJsonConfig fastJsonConfig = FastJsonUtils.defaultConfig();
                writer.write(JSONObject.toJSONString(resultData, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializerFeatures()));
            }
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
