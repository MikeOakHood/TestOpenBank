package com.capgemini.test.code.domain.service;


import com.capgemini.test.code.application.dto.CheckDniResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.test.code.domain.exception.UserAlreadyExistsException;
import com.capgemini.test.code.domain.exception.ValidationException;
import com.capgemini.test.code.infra.persistence.UserEntity;
import com.capgemini.test.code.domain.model.User;


@Service
public class UserDomainService {

    public void validateUser(User user, UserEntity userEntity, ResponseEntity<CheckDniResponse> response) {

        if (userEntity != null ) {
            throw new UserAlreadyExistsException(userEntity.getEmail());
        };

        if (response.getBody() != null && response.getBody().getMessage() !=null) {
            if (!response.getBody().getMessage().contains("Valid DNI")) {
                throw new ValidationException("dni", "error validation dni");
            }
        } else {
            throw new ValidationException("dni", "error validation dni");
        }

        if (user.getName() == null || user.getName().length() > 6) {
            throw new ValidationException("userName", "error length user");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new ValidationException("email", "error validation email");
        }

    }
}
