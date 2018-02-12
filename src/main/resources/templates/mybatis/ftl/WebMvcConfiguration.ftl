package ${packageName}.configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import ${packageName}.configuration.fastjson.FastJsonUtils;
import ${packageName}.configuration.mvc.JsonReturnValueHandler;
import ${packageName}.configuration.mvc.XmlReturnValueHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new JsonReturnValueHandler());
        returnValueHandlers.add(new XmlReturnValueHandler());
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        fastConverter.setFastJsonConfig(FastJsonUtils.defaultConfig());
        converters.add(fastConverter);

//        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
//        gsonConverter.setGson(GsonJsonUtils.defaultGson());
//        converters.add(gsonConverter);

        super.configureMessageConverters(converters);
    }


}
