package com.capgemini.test.code.infra.persistence;

import com.capgemini.test.code.model.Role;
import com.capgemini.test.code.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        RoomEntity roomEntity = new RoomEntity();
        //entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        entity.setRole(user.getRole().toString().toUpperCase());
        entity.setDni(user.getDni());
        roomEntity.setId(user.getRoomId());
        entity.setRoom(roomEntity);
        return entity;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User (
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhone(),
            Role.fromString(entity.getRole()),
            entity.getDni(),
            entity.getRoom().getId()
        );
    }
}
