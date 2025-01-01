package com.app.socialmediaapplication.model;

import com.app.socialmediaapplication.dto.UserDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")),
            @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    })
    private UserDto user;

    private String content;

    private Set<UserDto> likedByUsers = new HashSet<>();
}
