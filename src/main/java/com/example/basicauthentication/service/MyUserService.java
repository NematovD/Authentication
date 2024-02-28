package com.example.basicauthentication.service;

import com.example.basicauthentication.entity.User;

public interface MyUserService {
    User create(User user);
    User getUser(Integer id);
    void DeleteUser(Integer id);
}
