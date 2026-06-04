package com.gunjan.airesumeanalyzer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gunjan.airesumeanalyzer.entity.User;
import com.gunjan.airesumeanalyzer.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}