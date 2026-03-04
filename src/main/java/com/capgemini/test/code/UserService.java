package com.capgemini.test.code;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.test.code.errors.UserAlreadyExistsException;
import com.capgemini.test.code.errors.UserNotFoundException;
import com.capgemini.test.code.errors.ValidationException;
import com.capgemini.test.code.infra.notification.NotificationStrategy;
import com.capgemini.test.code.infra.persistence.UserEntity;
import com.capgemini.test.code.infra.persistence.UserMapper;
import com.capgemini.test.code.infra.persistence.UserRepository;
import com.capgemini.test.code.model.Role;
import com.capgemini.test.code.model.User;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final Map<Role, NotificationStrategy> notificationStrategies;
    private final CheckDniService checkDniService;

    public UserService(UserRepository userRepository,
                       Map<Role, NotificationStrategy> notificationStrategies,
                       UserMapper userMapper,
                       CheckDniService checkDniService) {
        this.userRepository = userRepository;
        this.notificationStrategies = notificationStrategies;
        this.userMapper = userMapper;
        this.checkDniService = checkDniService;
    }

    public Long createUser(User user) {
        validateUser(user);
        User saved = userMapper.toDomain(userRepository.save(userMapper.toEntity(user)));
        notifyUser(saved);
        return saved.getId();
    }

    public User getUser(Long userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByIdAndRoom_Id(userId, 1L);
        return optionalUserEntity.map(userMapper::toDomain).orElseThrow(() -> new UserNotFoundException(userId));
    }

    private void validateUser(User user) {

        if (user.getName() == null || user.getName().length() > 6) {
            throw new ValidationException("userName", "error length user");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new ValidationException("email", "error validation email");
        }

        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(user.getEmail());
        optionalUserEntity.ifPresent((userEntity) -> {
            throw new UserAlreadyExistsException(userEntity.getEmail());
        });

        CheckDniRequest checkDniRequest = new CheckDniRequest();
        checkDniRequest.setDni(user.getDni());
        if (!checkDniService.isValid(checkDniRequest)) {
            throw new ValidationException("dni", "error validation dni");
        }

    }

    private void notifyUser(User user) {
        NotificationStrategy strategy = notificationStrategies.get(user.getRole());
        if (strategy != null) {
            strategy.send(user);
        }
    }
}
