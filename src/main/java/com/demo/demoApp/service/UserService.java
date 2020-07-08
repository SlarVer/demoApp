package com.demo.demoApp.service;

import com.demo.demoApp.entities.User;
import com.demo.demoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean saveUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public String loadUser(String username, String password){
        if (userRepository.findByUsername(username) == null){
            return "Incorrect username";
        } else if (userRepository.findByUsernameAndPassword(username, password) == null){
            return "Incorrect password";
        } else return "Correct";
    }
}
