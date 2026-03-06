package com.capgemini.test.code.infra.policy;

import com.capgemini.test.code.application.dto.CheckDniResponse;
import com.capgemini.test.code.domain.exception.ValidationException;
import com.capgemini.test.code.domain.policy.VerifyDNI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RemoteVerifyDNI implements VerifyDNI {


    @Override
    public boolean isValid (ResponseEntity<CheckDniResponse> response) {

        if (response.getBody() != null && response.getBody().getMessage() !=null) {
            if (!response.getBody().getMessage().contains("Valid DNI")) {
                throw new ValidationException("dni", "error validation dni");
            }
        } else {
            throw new ValidationException("dni", "error validation dni");
        }
        return false;
    }
}
