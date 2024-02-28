package com.example.basicauthentication.service;

import com.example.basicauthentication.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User getById(Long id);

    List<User> getAll();

    void deleteById(Long id);

    User update(User user);
}
