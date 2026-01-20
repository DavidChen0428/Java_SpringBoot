package com.david.project.service;

import com.david.project.domain.User;
import com.david.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public void createUser(User user) {
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
    @Transactional
    public void updateUser(User user) {
        User userOri = getUserById(user.getId());
        if (userOri == null) {
            throw new RuntimeException("User not found.");
        } else {
            userRepository.save(user);
        }
    }

    // DELETE
    @Transactional
    public void deleteUserById(Integer id) {
        User userOri = getUserById(id);
        if (userOri == null) {
            throw new RuntimeException("User not found.");
        } else {
            userRepository.deleteById(id);
        }
    }
}
