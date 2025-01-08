package com.app.socialmediaapplication.service;

import com.app.socialmediaapplication.dto.UserDto;
import com.app.socialmediaapplication.exception.UserException;
import com.app.socialmediaapplication.model.User;
import com.app.socialmediaapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist.isPresent()){
            throw new UserException("Email already registered");
        }

        Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
        if(isUsernameExist.isPresent()){
            throw new UserException("Username already exists");
        }

        if(user.getEmail()==null || user.getPassword()==null || user.getUsername()==null || user.getName()==null){
            throw new UserException("All fields are mandatory");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("User does not exist with id:"+id);
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
        User reqUser = findUserById(regUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUsername(reqUser.getUsername());

        UserDto following = new UserDto();
        following.setEmail(follower.getEmail());
        following.setId(follower.getId());
        following.setUserImage(follower.getUserImage());
        following.setName(follower.getName());
        following.setUsername(follower.getUsername());

        reqUser.getFollowing().add(following);
        followUser.getFollower().add(follower);

        userRepository.save(reqUser);
        userRepository.save(followUser);
        return "You are now following "+followUser.getUsername();
    }

    @Override
    public String unfollowUser(Integer regUserId, Integer followUserId) throws UserException {
        User reqUser = findUserById(regUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUsername(reqUser.getUsername());

        UserDto following = new UserDto();
        following.setEmail(follower.getEmail());
        following.setId(follower.getId());
        following.setUserImage(follower.getUserImage());
        following.setName(follower.getName());
        following.setUsername(follower.getUsername());

        reqUser.getFollowing().remove(following);
        followUser.getFollower().remove(follower);

        userRepository.save(reqUser);
        userRepository.save(followUser);
        return "You have Unfollowed "+followUser.getUsername();
    }

    @Override
    public List<User> findUserByIds(List<Integer> userIds) throws UserException {
        List<User> users = userRepository.findAllUsersByUserIds(userIds);
        return users;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRepository.findByQuery(query);
        if(users.size()==0){
            throw new UserException("User not found");
        }
        return users;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        return null;
    }
}
