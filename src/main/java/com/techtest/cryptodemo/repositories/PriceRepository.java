package com.techtest.cryptodemo.repositories;

import com.techtest.cryptodemo.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findTopByCryptoTypeOrderByTimestampDesc(String cryptoType);
}
