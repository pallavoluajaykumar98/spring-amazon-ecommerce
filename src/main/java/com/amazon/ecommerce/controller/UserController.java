package com.amazon.ecommerce.controller;

import com.amazon.ecommerce.model.User;
import com.amazon.ecommerce.service.UserDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserDetailService userService;

    public UserController(UserDetailService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser() {
        return userService.findByUsername("testUser");
    }
}
