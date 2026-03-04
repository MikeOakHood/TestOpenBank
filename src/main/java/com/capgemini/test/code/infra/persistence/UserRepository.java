package com.capgemini.test.code.infra.persistence;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdAndRoom_Id(Long id, Long roomId);

    Optional<UserEntity> findByEmail(String email);
}
