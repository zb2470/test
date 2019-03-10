package com.test.test.service;

import com.test.test.Model.User;
import com.test.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    public User findById(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            return userOpt.get();
        }
        throw new RuntimeException("XXX");
    }
}
