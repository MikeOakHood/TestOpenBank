package com.capgemini.test.code.infra.persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
