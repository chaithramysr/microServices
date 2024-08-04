package com.example.userService.controller;

import com.example.userService.entity.User;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUserDetsilsUsingFeign(@PathVariable("id") String userId){
        return userService.getUserUsingFeign(userId);
    }

    @GetMapping("/getUserById/{id}")
    public User getUser(@PathVariable("id") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") String userId){
        return userService.deleteUser(userId);
    }
}
