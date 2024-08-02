package com.example.userService.service;

import com.example.userService.entity.User;
import com.example.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String addUser(User user) {
        userRepository.save(user);
        return "User added successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).get();
    }

    public String updateUser(User user) {
        userRepository.save(user);
        return "User updated successfully";
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
}


