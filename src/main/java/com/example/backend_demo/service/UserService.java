package com.example.backend_demo.service;

import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.User;

public interface UserService {
    public User findUserById(Long id) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
