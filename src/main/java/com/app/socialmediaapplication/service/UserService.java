package com.app.socialmediaapplication.service;

import com.app.socialmediaapplication.exception.UserException;
import com.app.socialmediaapplication.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public User registerUser(User user) throws UserException;
    public User findUserById(Integer id) throws UserException;
    public User findUserProfile(String token) throws UserException;
    public User findUserByUsername(String username) throws UserException;
    public String followUser(Integer regUserId, Integer followUserId) throws UserException;
    public String unfollowUser(Integer regUserId, Integer followUserId) throws UserException;
    public List<User> findUserByIds(List<Integer> userIds) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException;


}
