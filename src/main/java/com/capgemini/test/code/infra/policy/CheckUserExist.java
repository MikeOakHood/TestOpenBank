package com.capgemini.test.code.infra.policy;

import com.capgemini.test.code.domain.exception.UserAlreadyExistsException;
import com.capgemini.test.code.domain.model.User;
import com.capgemini.test.code.domain.policy.CheckUser;
import com.capgemini.test.code.infra.persistence.UserEntity;
import com.capgemini.test.code.infra.persistence.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CheckUserExist implements CheckUser {

    private final UserRepository userRepository;

    public CheckUserExist(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean ivValid(User user) {

        UserEntity userEntity = userRepository.findByEmail(user.getEmail()).orElse(null);

        if (userEntity == null ) {
            return true;
        } else {
            throw new UserAlreadyExistsException(userEntity.getEmail());
        }
    }
}
