package com.app.socialmediaapplication.repository;

import com.app.socialmediaapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Finds a user by their email address
    public Optional<User> findByEmail(String email);
    // Finds a user by their username
    public Optional<User> findByUsername(String username);

    // Finds all users whose IDs are in the provided list
    @Query("SELECT u FROM User u WHERE u.id IN :users")
    public List<User> findAllUsersByUserIds(@Param("users") List<Integer> userIds);

    // Finds users whose username, name, or email contains the provided query string
    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.name LIKE %:query% OR u.email LIKE %:query%")
    public List<User> findByQuery(@Param("query") String query);

}
