package com.gunjan.airesumeanalyzer.controller;

import java.util.List;

import java.util.Map;
import com.gunjan.airesumeanalyzer.dto.LoginRequest;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.gunjan.airesumeanalyzer.dto.SignupRequest;
import com.gunjan.airesumeanalyzer.entity.User;
import com.gunjan.airesumeanalyzer.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody SignupRequest request) {

        try {

            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());

            userService.registerUser(user);

            return ResponseEntity.ok(
                    "User registered successfully");

        } catch (RuntimeException ex) {

            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        try {

            User user = userService.loginUser(
                    request.getEmail(),
                    request.getPassword());

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful",
                            "userId", user.getId(),
                            "name", user.getName(),
                            "email", user.getEmail()));

        } catch (RuntimeException ex) {

            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}