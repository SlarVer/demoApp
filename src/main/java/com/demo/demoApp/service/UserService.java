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

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setBlocked(false);
        userRepository.save(user);
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public User loadUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User \"" + username + "\" not found");
        }
        else if (user.isBlocked()) {
            throw new UsernameNotFoundException("User blocked");
        }
        return user;
    }

    @Transactional
    public boolean blockUser(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User \"" + username + "\" not found");
        } else if (!user.isBlocked()) {
            user.setBlocked(true);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean unlockUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User \"" + username + "\" not found");
        } else if (user.isBlocked()){
            user.setBlocked(false);
            return true;
        }
        return false;
    }
}
