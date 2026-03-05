package com.capgemini.test.code.infra.notification;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.capgemini.test.code.domain.model.User;

public class EmailNotificationStrategy implements NotificationStrategy {

    @Value("${external.service.url}")
    private String emailUrl;

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationStrategy.class);
    private final RestTemplate restTemplate;


    public EmailNotificationStrategy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    //public void send(User user, String message) {
    public void send(User user) {
        String url = UriComponentsBuilder.fromHttpUrl(emailUrl).path("/email").toUriString();
        Map<String, String> body = Map.of(
                "message", user.getEmail()
        );

        HttpHeaders headers = new HttpHeaders();
        headers. setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Email provider error");
        }

    }
}

