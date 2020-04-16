package com.example.bankSpring.Service;


import com.example.bankSpring.Entity.User;
import com.example.bankSpring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String username) {
        List<User> users = userRepository.findByLogin(username);
        return users.get(0);
    }

    @Override
    public User findByPhone(String phone) {
        List<User> users = userRepository.findByPhone(phone);
        return users.get(0);
    }
}