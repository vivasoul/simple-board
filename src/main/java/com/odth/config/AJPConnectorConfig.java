package com.odth.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class AJPConnectorConfig {

    @Value("${tomcat.ajp.address}")
    private String address;
    @Value("${tomcat.ajp.protocol}")
    private String protocol;
    @Value("${tomcat.ajp.port}")
    private int port;
    @Value("${tomcat.ajp.allowedRequestAttributesPattern}")
    private String allowedRequestAttributesPattern;

    @Bean
    public ServletWebServerFactory servletConainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createAJPConnector());
        return tomcat;
    }

    private Connector createAJPConnector() {
        Connector ajpConnector = new Connector(protocol);
        ajpConnector.setProperty("address",address);
        ajpConnector.setProperty("allowedRequestAttributesPattern", allowedRequestAttributesPattern);
        ajpConnector.setPort(port);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        ((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setSecretRequired(false);
        return ajpConnector;
    }
}
