package com.gunjan.airesumeanalyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gunjan.airesumeanalyzer.entity.User;
import com.gunjan.airesumeanalyzer.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
