package com.capgemini.test.code.infra.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@Configuration
public class ApplicationConfig {

    @Value("${dni.validation.url:http://localhost:8089}")
    private String dniValidationUrl;

    @Bean
    public RestTemplate restTemplate() {

        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(5000);
        return new RestTemplate(factory);
    }
    
}
