package com.david.project.service;

import com.david.project.entity.User;
import com.david.project.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public void createUser(@Valid User user) {
        userRepository.save(user);
    }

    // SELECT
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // UPDATE
    public void updateUser(@Valid User user) {
        userRepository.save(user);
    }

    // DELETE
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
