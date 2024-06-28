package com.vm.auth.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

//    @Resource
//    private Environment environment; // environment.getActiveProfiles()[0]

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Data warehouse API")
                        .version("1.0")
                        .description("This is a document RESTful service using springdoc-openapi and OpenAPI 3."))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi vmOpenApi() {
        return GroupedOpenApi.builder()
                .group("api-docs")
                .pathsToMatch("/**")
                .build();
    }
}