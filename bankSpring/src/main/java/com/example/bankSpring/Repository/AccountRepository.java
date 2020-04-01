package com.example.bankSpring.Repository;

import com.example.bankSpring.Entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
    Optional<Account> findById(UUID uuid);
}
