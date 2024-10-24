package com.techtest.cryptodemo.repositories;

import com.techtest.cryptodemo.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
