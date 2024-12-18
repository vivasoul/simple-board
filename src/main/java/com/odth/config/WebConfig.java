package com.odth.config;

import com.odth.common.listener.DailyVisitListener;
import com.odth.common.mapper.CommonMapper;
import com.odth.common.view.FixedUrlBasedView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
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

    @Value("${upload.temp.up-path}")
    private String UPLOAD_TEMP_ROOT;

    @Value("${upload.temp.down-path}")
    private String DOWNLOAD_TEMP_ROOT;

    @Value("${upload.ops.up-path}")
    private String UPLOAD_ROOT;

    @Value("${upload.ops.down-path}")
    private String DOWNLOAD_ROOT;

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

        registry.addResourceHandler("/favicon/**")
                .addResourceLocations("classpath:/static/favicon/");

        registry.addResourceHandler(DOWNLOAD_TEMP_ROOT+"/**")
                .addResourceLocations("file:///"+UPLOAD_TEMP_ROOT+"/");

        registry.addResourceHandler(DOWNLOAD_ROOT+"/**")
                .addResourceLocations("file:///"+UPLOAD_ROOT+"/");
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

    @Bean
    public ServletListenerRegistrationBean<DailyVisitListener> sessionListenerDailyVisit(ApplicationContext applicationContext) {
        ServletListenerRegistrationBean<DailyVisitListener> listenerRegBean =
                new ServletListenerRegistrationBean<>();
        CommonMapper commonMapper = applicationContext.getBean(CommonMapper.class);
        listenerRegBean.setListener(new DailyVisitListener(commonMapper));
        return listenerRegBean;
    }
}



