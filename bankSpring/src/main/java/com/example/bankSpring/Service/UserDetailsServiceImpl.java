package com.example.bankSpring.Service;

import com.example.bankSpring.Entity.User;
import com.example.bankSpring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) {
        List<User> users = userRepository.findByLogin(login);
        if (users.size() == 0) throw new UsernameNotFoundException(login);
        User user = users.get(0);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new HashSet<>());
    }
}