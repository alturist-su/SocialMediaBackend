package com.app.socialmediaapplication.service;

import com.app.socialmediaapplication.exception.UserException;
import com.app.socialmediaapplication.model.User;

import java.util.List;

public class UserServiceImplementation implements UserService{

    @Override
    public User registerUser(User user) throws UserException {
        return null;
    }

    @Override
    public User findUserById(Integer id) throws UserException {
        return null;
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        return null;
    }

    @Override
    public String followUser(Integer regUserId, Integer followUserId) throws UserException {
        return "";
    }

    @Override
    public String unfollowUser(Integer regUserId, Integer followUserId) throws UserException {
        return "";
    }

    @Override
    public List<User> findUserByIds(List<Integer> userIds) throws UserException {
        return List.of();
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        return List.of();
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        return null;
    }
}
