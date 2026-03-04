package com.capgemini.test.code.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {
    @Getter
    private final Long id;
    private final List<User> users;

    public Room(Long id) {
        this.id = id;
        this.users = new ArrayList<>();
    }

    public Room(Long id, List<User> users) {
        this.id = id;
        this.users = new ArrayList<>(users);
    }

    public List<User> getUsers() { return Collections.unmodifiableList(users); }
    public void addUser(User user) { this.users.add(user); }
}
