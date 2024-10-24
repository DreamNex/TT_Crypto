package com.techtest.cryptodemo.service;

import com.techtest.cryptodemo.DTO.AggregatedPriceDTO;

public interface TradeService {

    String buyCrypto(String cryptoType, double amount, Long userId);
    String sellCrypto(String cryptoType, double amount, Long userId);
    void recordTransaction(Long userId, String cryptoType, String transactionType, double amount, double price);
    AggregatedPriceDTO getLatestAgPrice(String cryptoType);
}
