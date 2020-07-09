package com.demo.demoApp.service;

import com.demo.demoApp.entities.Role;
import com.demo.demoApp.entities.User;
import com.demo.demoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
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

    public List<User> allUsers(){
        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }

        return user;
    }
}
