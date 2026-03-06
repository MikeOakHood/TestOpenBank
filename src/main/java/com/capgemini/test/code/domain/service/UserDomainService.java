package com.capgemini.test.code.domain.service;


import com.capgemini.test.code.application.dto.CheckDniResponse;
import com.capgemini.test.code.domain.policy.CheckEmail;
import com.capgemini.test.code.domain.policy.CheckName;
import com.capgemini.test.code.domain.policy.CheckUser;
import com.capgemini.test.code.domain.policy.VerifyDNI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.test.code.domain.exception.UserAlreadyExistsException;
import com.capgemini.test.code.domain.exception.ValidationException;
import com.capgemini.test.code.infra.persistence.UserEntity;
import com.capgemini.test.code.domain.model.User;


@Service
public class UserDomainService {

    private final VerifyDNI verifyDNI;
    private final CheckUser checkUser;
    private final CheckName checkName;
    private final CheckEmail checkEmail;

    public UserDomainService(VerifyDNI verifyDNI, CheckUser checkUser, CheckName checkName, CheckEmail checkEmail) {
        this.verifyDNI = verifyDNI;
        this.checkUser = checkUser;
        this.checkName = checkName;
        this.checkEmail = checkEmail;
    }


    public void validateUser(User user) {

        boolean valid = false;

        valid = checkUser.ivValid(user);
        if  (valid) {
            verifyDNI.isValid(user);
        }
        if (valid)  {
            checkName.isValid(user.getName());
        }
        if (valid) {
            checkEmail.isValid(user.getEmail());
        }

    }
}
