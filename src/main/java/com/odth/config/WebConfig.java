package com.odth.config;

import com.odth.common.view.FixedUrlBasedView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.AbstractView;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${nexacro.main.path}")
    private String mainUri;

    @Value("${nexacro.exception.default.message}")
    private String nexacroExceptionDefaultMessage;

    @Bean(name = "nexaMain")
    public AbstractView getNexaMainView() {

        AbstractView nexaView = new FixedUrlBasedView(mainUri);

        return nexaView;
    }

    @Bean
    @ConditionalOnMissingBean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(LocaleContextHolder.getLocale());
        return localeResolver;
    }


    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //@Todo. move to spring-security configuration

        registry.addResourceHandler("/static/vue/**")
                .addResourceLocations("classpath:/vue/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("/nowhere");

    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers) {
        /* DO NOTHING */
    }

    @Override
    public void addReturnValueHandlers(
            List<HandlerMethodReturnValueHandler> handlers) {
        /* DO NOTHING */
    }


    @Override
    public void configureHandlerExceptionResolvers(
            List<HandlerExceptionResolver> resolvers) {
        /* DO NOTHING */
    }
}



