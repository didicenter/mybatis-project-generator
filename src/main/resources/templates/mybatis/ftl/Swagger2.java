package com.hdd.all.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

import static com.google.common.collect.Lists.newArrayList;


@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("v1")
                .globalResponseMessage(
                        RequestMethod.GET, newArrayList(
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("请求失败")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("服务器繁忙")
                                        .responseModel(new ModelRef("Error"))
                                        .build())
                )
                .globalResponseMessage(
                        RequestMethod.POST, newArrayList(
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("请求失败")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("服务器繁忙")
                                        .responseModel(new ModelRef("Error"))
                                        .build())
                )
                .globalResponseMessage(
                        RequestMethod.PUT, newArrayList(
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("请求失败")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("服务器繁忙")
                                        .responseModel(new ModelRef("Error"))
                                        .build())
                )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/ms_order/v1/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("订单系统v1版本使用详情")
//                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
//                .termsOfServiceUrl("http://blog.didispace.com/")
                .version("0.1")
                .build();
    }

    @Bean
    UiConfiguration uiConfiguration() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                10 * 1000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }


}
