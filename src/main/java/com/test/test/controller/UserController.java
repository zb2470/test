package com.test.test.controller;

import com.test.test.Model.User;
import com.test.test.Reponses.UserResponse;
import com.test.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable(value = "id") long userId) {
        User user = userService.findById(userId);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
