package com.redis.pubsub.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Pub sub API")
                        .description("The simple application for pub sub with redis and spring")
                        .version("v1.0.0")
                        .license(new License().name("springdoc").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Poc for pub sub with redis and spring")
                        .url("https://spring.io/"));
    }
}
