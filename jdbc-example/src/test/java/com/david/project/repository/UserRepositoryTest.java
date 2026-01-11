package com.david.project.repository;

import com.david.project.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        User user = new User("Sarah Connor", "Female", "555-1234", "sarah@dccompany.com", "123 Future St, Los Angeles, CA");
        userRepository.save(user);
        logger.info("User saved successfully.");
    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        for(User user : users) {
            logger.info(user.toString());
        }
    }

    @Test
    void findById() {
        User user = userRepository.findById(1);
        logger.info(user.toString());
    }

    @Test
    void update() {
        User user = new User(1, "John Doe", "Male", "123-4567", "john@dccompany.com", "456 Main St, Springfield, IL");
        userRepository.update(user);
        logger.info("User updated successfully.");
    }

    @Test
    void deleteById() {
        userRepository.deleteById(7);
        logger.info("User deleted successfully.");
    }
}