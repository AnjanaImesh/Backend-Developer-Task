package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("backend API")
                        .version("v1.0")
                        .description("API documentation for backend")
                        .contact(new Contact()
                                .name("Your Name")
                                .url("www.example.com")
                                .email("contact@example.com")
                        )
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("backend-public")
                .packagesToScan("com.example.backend.controller")
                .build();
    }
}
