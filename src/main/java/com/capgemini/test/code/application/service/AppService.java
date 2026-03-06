package com.capgemini.test.code.application.service;

import com.capgemini.test.code.domain.exception.UserNotFoundException;
import com.capgemini.test.code.domain.model.Role;
import com.capgemini.test.code.domain.model.User;
import com.capgemini.test.code.domain.service.UserDomainService;
import com.capgemini.test.code.infra.notification.NotificationStrategy;
import com.capgemini.test.code.infra.persistence.UserEntity;
import com.capgemini.test.code.infra.persistence.UserRepository;
import com.capgemini.test.code.infra.persistence.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class AppService {

    private final UserDomainService userDomainService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final Map<Role, NotificationStrategy> notificationStrategies;

    public AppService(UserDomainService userDomainService,
                      UserRepository userRepository,
                      Map<Role, NotificationStrategy> notificationStrategies,
                      UserMapper userMapper) {
        this.userDomainService      = userDomainService;
        this.userRepository         = userRepository;
        this.notificationStrategies = notificationStrategies;
        this.userMapper             = userMapper;
    }

    public Long createUser(User user) {

        // Llamar al dominio
        userDomainService.validateUser(user);

        // Guardar usuario en BBDD
        User saved = userMapper.toDomain(userRepository.save(userMapper.toEntity(user)));

        // Notificar
        notifyUser(user);

        // Devlver resultado
        return saved.getId();
    }


    public User getUser(Long userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByIdAndRoom_Id(userId, 1L);
        return optionalUserEntity.map(userMapper::toDomain).orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Notificacion
    private void notifyUser(User user) {
        NotificationStrategy strategy = notificationStrategies.get(user.getRole());
        if (strategy != null) {
            strategy.send(user);
        }
    }

}

