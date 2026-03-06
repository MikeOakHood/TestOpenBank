package com.capgemini.test.code.application.service;



import com.capgemini.test.code.application.dto.CheckDniRequest;
import com.capgemini.test.code.application.dto.CheckDniResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.capgemini.test.code.domain.exception.ValidationException;


@Service
public class CheckDniService {

    @Value("${external.service.url}")
    private String dniValidationUrl;

    private final RestTemplate restTemplate;

    public CheckDniService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<CheckDniResponse> resultadoChekExterno(CheckDniRequest checkDniRequest) {
        boolean valid = false;
        String url = UriComponentsBuilder.fromHttpUrl(dniValidationUrl).path("/check-dni").toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CheckDniRequest> requestEntity = new HttpEntity<>(checkDniRequest, headers);

        ResponseEntity<CheckDniResponse> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, CheckDniResponse.class);

        } catch (Exception e) {
            throw new ValidationException("email", "error checking dni");
        }
        return response;
    }

}