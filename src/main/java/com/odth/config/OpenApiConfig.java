package com.odth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class OpenApiConfig {
    private final Environment env;

    public OpenApiConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public OpenAPI openApi(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info().title("OYWMS Api - " +  env.getActiveProfiles()[0])
                        .version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                ;
    }
}
