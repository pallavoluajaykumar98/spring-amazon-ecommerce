package com.amazon.ecommerce.service;

import com.amazon.ecommerce.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {
    public User findByUsername(String username) {
        return new User(username, "dummyPassword");
    }
}
