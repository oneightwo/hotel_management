package com.hotel_management.service.impl;

import com.hotel_management.model.User;
import com.hotel_management.repository.UserRepository;
import com.hotel_management.security.SecurityUtils;
import com.hotel_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Пользователь не найден");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    @Override
    public User save(User user) {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.nonNull(userFromContext)) {
            user.setCreatedBy(new User().setId(userFromContext.getId()));
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        getById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        userRepository.findByCreatedById(id)
                .forEach(user -> {
                    user.setCreatedBy(new User().setId(1L));
                    userRepository.save(user);
                });
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getByCreateById(Long id) {
        return userRepository.findByCreatedById(id);
    }
}
