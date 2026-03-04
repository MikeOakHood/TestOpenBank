package com.capgemini.test.code;

import com.capgemini.test.code.errors.ValidationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;


@Service
public class CheckDniService {

    @Value("${external.service.url}")
    private String dniValidationUrl;

    private final RestTemplate restTemplate;

    public CheckDniService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValid (CheckDniRequest checkDniRequest) {
        boolean valid = false;
        String url = UriComponentsBuilder.fromHttpUrl(dniValidationUrl).path("/check-dni").toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CheckDniRequest> requestEntity = new HttpEntity<>(checkDniRequest, headers);

        try {
            ResponseEntity<CheckDniResponse> response = restTemplate.exchange(url,HttpMethod.PATCH,requestEntity,CheckDniResponse.class);
            if (response.getBody() != null && response.getBody().getMessage() !=null) {
                valid = response.getBody().getMessage().contains("Valid DNI");
            }
        } catch (Exception e) {
            throw new ValidationException("email", "error checking dni");
        }
        return valid;
    }

}
