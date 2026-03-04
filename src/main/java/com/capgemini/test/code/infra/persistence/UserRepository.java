package com.capgemini.test.code.infra.persistence;


import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdAndRoom_Id(Long id, Long roomId);

    Optional<UserEntity> findByEmail(String email);
}
