package com.process.shop.controller;

import com.process.shop.model.User;
import com.process.shop.model.dto.AuthRequest;
import com.process.shop.model.dto.AuthResponse;
import com.process.shop.service.AuthService;
import com.process.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(service.login(authRequest));
    }

    @PostMapping("register")
    public ResponseEntity<User> RegisterUser(@RequestBody @Valid User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }
}











