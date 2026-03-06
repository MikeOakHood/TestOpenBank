package com.capgemini.test.code.infra.policy;

import com.capgemini.test.code.domain.exception.ValidationException;
import com.capgemini.test.code.domain.policy.CheckEmail;
import org.springframework.stereotype.Component;

@Component
public class CheckEmailFormat implements CheckEmail {

    @Override
    public boolean isValid(String mail) {

        if (mail == null || !mail.contains("@") || !mail.contains(".")) {
            throw new ValidationException("email", "error validation email");
        } else {
            return false;
        }
    }
}
