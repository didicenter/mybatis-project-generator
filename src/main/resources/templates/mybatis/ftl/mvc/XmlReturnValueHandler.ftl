package ${packageName}.configuration.mvc;

import com.thoughtworks.xstream.XStream;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


public class XmlReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        WrapResult wrapResult = returnType.getMethodAnnotation(WrapResult.class);
        return Objects.nonNull(wrapResult) && WrapResult.Type.xml == wrapResult.value();
    }

    @Override
    public void handleReturnValue(Object obj, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        response.setCharacterEncoding("UTF-8");
        ResultData resultData = new ResultData();
        resultData.setData(obj);
        if (PlainValue.class.isInstance(obj)) {
            PlainValue text = (PlainValue) obj;
            resultData.setData(text.getValue());
        }
        try (PrintWriter writer = response.getWriter()) {

            XStream xStream = XStreamUtils.customerXStream();
            writer.write(xStream.toXML(resultData));
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}
