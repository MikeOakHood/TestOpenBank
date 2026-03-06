package com.capgemini.test.code.domain.policy;

import com.capgemini.test.code.domain.model.User;


public interface VerifyDNI {

    public boolean isValid (User user);

}
