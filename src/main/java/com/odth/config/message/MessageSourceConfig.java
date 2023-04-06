package com.odth.config.message;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource
                .addBasenames("classpath:/messages/message");
        reloadableResourceBundleMessageSource
                .setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        return reloadableResourceBundleMessageSource;
    }

}
