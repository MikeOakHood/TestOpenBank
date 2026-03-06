package com.capgemini.test.code.infra.policy;

import com.capgemini.test.code.domain.exception.UserAlreadyExistsException;
import com.capgemini.test.code.domain.policy.CheckUser;
import com.capgemini.test.code.infra.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckUserExist implements CheckUser {

    @Override
    public boolean ivValid(UserEntity userEntity) {

        if (userEntity == null ) {
            return true;
        } else {
            throw new UserAlreadyExistsException(userEntity.getEmail());
        }
    }
}
