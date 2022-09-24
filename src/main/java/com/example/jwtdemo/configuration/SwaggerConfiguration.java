package com.example.jwtdemo.configuration;

import com.example.jwtdemo.repositories.UserRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.EntityModel;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
@ApiModel(value = "com.example.jwtdemo.entities")
public class SwaggerConfiguration {

    @Bean
    public Docket getApiDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("controllers principaux")
                .ignoredParameterTypes(UserRepository.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.jwtdemo.controllers"))
                .paths(PathSelectors.any())
                .build();
        return  docket;
    }

    @Bean
    public Docket getApiDockets(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("controllers login")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.jwtdemo.auth.api"))
                .paths(PathSelectors.any())
                .build();
        return  docket;
    }
}
