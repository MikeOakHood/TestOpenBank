package com.capgemini.test.code.domain.policy;

import com.capgemini.test.code.infra.persistence.UserEntity;

public interface CheckUser {

    public boolean ivValid (UserEntity userEntity);

}
