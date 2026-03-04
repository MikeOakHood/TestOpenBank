package com.capgemini.test.code.infra.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "room")
    private List<UserEntity> users = new ArrayList<>();

    public RoomEntity() {}

    public RoomEntity(Long id) { this.id = id; }

    /*public void setId(Long id) { this.id = id; }

    public void setUsers(List<UserEntity> users) { this.users = users; }*/
}
