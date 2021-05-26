package com.hotel_management.service;

import com.hotel_management.model.User;

import java.util.List;

public interface UserService extends CRUDService<User, Long> {

    List<User> getByCreateById(Long id);
}
