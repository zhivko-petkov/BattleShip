package com.example.examprepbattleship.service;

import com.example.examprepbattleship.model.entity.User;
import com.example.examprepbattleship.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);
    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(long id, String username);

    User findById(long id);

    boolean findByUsername(String username);

    boolean findByEmail(String email);
}
