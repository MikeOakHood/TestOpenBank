package com.capgemini.test.code.infra.notification;

import com.capgemini.test.code.domain.model.User;


public interface NotificationStrategy {
    //void send(User user, String message);
    void send(User user);
}
