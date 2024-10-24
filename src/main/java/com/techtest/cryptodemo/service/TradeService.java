package com.techtest.cryptodemo.service;

import com.techtest.cryptodemo.DTO.AggregatedPriceDTO;
import com.techtest.cryptodemo.DTO.ProfileDTO;
import com.techtest.cryptodemo.DTO.TransactionHistoryDTO;

import java.util.List;

public interface TradeService {

    String buyCrypto(String cryptoType, double amount, Long userId);
    String sellCrypto(String cryptoType, double amount, Long userId);
    void recordTransaction(Long userId, String cryptoType, String transactionType, double amount, double price);
    AggregatedPriceDTO getLatestAgPrice(String cryptoType);
    ProfileDTO getProfile(Long userId);
    List<TransactionHistoryDTO> getUserTradeHistory(Long userId);
}
