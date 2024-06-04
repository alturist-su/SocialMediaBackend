package com.app.socialmediaapplication.controller;

import com.app.socialmediaapplication.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//User controller class
@RestController
public class UserController {

    @GetMapping
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        User user1 = new User("John","K","john@email","johnpass");

        users.add(user1);

        return users;
    }

    @GetMapping("/url")
    public String getMappingWithUrl(){
        return "With url";
    }

    @GetMapping("/get")
    public String getText(){
        return "This is my text";
    }
}
