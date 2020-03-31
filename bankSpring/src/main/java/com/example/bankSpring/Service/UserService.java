package com.example.bankSpring.Service;


import com.example.bankSpring.Entity.User;

public interface UserService {
    void save(User user);

    User findByLogin(String login);

    User findByPhone(String phone);
}