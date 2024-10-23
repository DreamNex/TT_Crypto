package com.techtest.cryptodemo.repositories;

import com.techtest.cryptodemo.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
