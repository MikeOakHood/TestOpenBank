package com.capgemini.test.code.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ComponentScan
@Configuration
public class ApplicationConfig {

    @Value("${dni.validation.url:http://localhost:8089}")
    private String dniValidationUrl;


}
