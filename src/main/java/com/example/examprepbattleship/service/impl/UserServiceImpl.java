package com.example.examprepbattleship.service.impl;

import com.example.examprepbattleship.model.entity.User;
import com.example.examprepbattleship.model.service.UserServiceModel;
import com.example.examprepbattleship.repository.UserRepository;
import com.example.examprepbattleship.security.CurrentUser;
import com.example.examprepbattleship.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public User findById(long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean findByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
