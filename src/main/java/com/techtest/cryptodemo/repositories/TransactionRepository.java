package com.techtest.cryptodemo.repositories;

import com.techtest.cryptodemo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
