package com.example.bankSpring.Repository;

import java.util.List;

import com.example.bankSpring.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByLogin(String login);

    List<User> findByPhone(String phone);
}