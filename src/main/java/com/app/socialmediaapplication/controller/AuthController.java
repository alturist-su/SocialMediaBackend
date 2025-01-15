// src/main/java/com/app/socialmediaapplication/controller/AuthController.java
package com.app.socialmediaapplication.controller;

import com.app.socialmediaapplication.exception.UserException;
import com.app.socialmediaapplication.model.User;
import com.app.socialmediaapplication.repository.UserRepository;
import com.app.socialmediaapplication.service.JwtTokenGeneratorService;
import com.app.socialmediaapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenGeneratorService jwtTokenGeneratorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUserHandler(@RequestBody User user) throws UserException {
        User createdUser = userService.registerUser(user);
        String token = jwtTokenGeneratorService.generateToken(createdUser.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> signinHandler(Authentication auth) throws BadCredentialsException {
        Optional<User> opt = userRepository.findByEmail(auth.getName());
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.ACCEPTED);
        }

        throw new BadCredentialsException("Invalid Username or Password");
    }
}