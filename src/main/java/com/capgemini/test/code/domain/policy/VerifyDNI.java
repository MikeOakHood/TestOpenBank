package com.capgemini.test.code.domain.policy;

import com.capgemini.test.code.application.dto.CheckDniResponse;
import org.springframework.http.ResponseEntity;

public interface VerifyDNI {

    boolean isValid (ResponseEntity<CheckDniResponse> response);

}
