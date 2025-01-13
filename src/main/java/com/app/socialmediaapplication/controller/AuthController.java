package com.app.socialmediaapplication.controller;

import com.app.socialmediaapplication.exception.UserException;
import com.app.socialmediaapplication.model.User;
import com.app.socialmediaapplication.repository.UserRepository;
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

    @PostMapping("/register")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException{
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> signinHandler(Authentication auth) throws BadCredentialsException{
        Optional<User> opt = userRepository.findByEmail(auth.getName());
        if(opt.isPresent()){
            return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
        }

        throw new BadCredentialsException("Invalid Username or Password");
    }
}
