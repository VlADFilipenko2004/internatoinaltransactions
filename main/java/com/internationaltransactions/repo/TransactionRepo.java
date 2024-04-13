package com.internationaltransactions.repo;

import com.internationaltransactions.model.Transaction;
import com.internationaltransactions.model.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByStatus(TransactionStatus status);
}