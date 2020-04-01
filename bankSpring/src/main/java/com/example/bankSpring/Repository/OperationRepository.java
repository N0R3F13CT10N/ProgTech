package com.example.bankSpring.Repository;

import com.example.bankSpring.Entity.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OperationRepository extends CrudRepository<Operation, Integer> {
    Optional<Operation> findById(Integer id);
}
