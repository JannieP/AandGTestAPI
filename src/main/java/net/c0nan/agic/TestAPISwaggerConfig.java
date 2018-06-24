package net.c0nan.agic;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class TestAPISwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo;
        apiInfo = new ApiInfo(
                "Auto & General test API",
                "This is an example implementation of API for testing candidates",
                "1.0",
                "Terms of service",
                new Contact("Jan Pieterse", "https://c0nan.net/", "jannie@pieterse.me"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
        return apiInfo;
    }
}
