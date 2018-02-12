package ${packageName}.configuration;

import ${packageName}.configuration.mvc.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ExceptionHandlerConfiguration {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultData errorHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception {
        ResultData data = new ResultData();
        data.setStatus(ResultData.Status.FAIL);
        data.setData(exception.getMessage());
        return data;

    }

}

