package com.capgemini.test.code.infra.policy;

import com.capgemini.test.code.domain.exception.ValidationException;
import com.capgemini.test.code.domain.policy.CheckName;
import org.springframework.stereotype.Component;

@Component
public class CheckNameLength implements CheckName {


    @Override
    public boolean isValid(String name) {

        if (name == null || name.length() > 6) {
            throw new ValidationException("userName", "error length user");
        } else {
            return true;
        }

    }
}
