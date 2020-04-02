package com.example.bankSpring.Repository;

import com.example.bankSpring.Entity.Account;
import com.example.bankSpring.Entity.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends CrudRepository<Operation, Integer> {
    Optional<Operation> findById(Integer id);

    List<Operation> findByAccTo(Account acc);

    List<Operation> findByAccFrom(Account acc);
}
