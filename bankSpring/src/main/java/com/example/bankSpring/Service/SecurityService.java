package com.example.bankSpring.Service;


public interface SecurityService {
    String findLoggedInLogin();

    void autoLogin(String login, String password);
}