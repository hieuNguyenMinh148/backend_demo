package com.example.backend_demo.service.impl;

import com.example.backend_demo.config.JwtProvider;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.User;
import com.example.backend_demo.repository.UserRepository;
import com.example.backend_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserById(Long id) throws UserException {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        throw new UserException("User not found with id - " + id);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email - " + email);
        }
        return user;
    }
}
