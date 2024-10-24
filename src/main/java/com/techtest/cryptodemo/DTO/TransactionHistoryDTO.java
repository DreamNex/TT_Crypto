package com.techtest.cryptodemo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class TransactionHistoryDTO {

    @JsonIgnore
    private Long userId;
    private String userName;
    private String cryptoType;
    private String transactionType;
    private double amount;
    private double price;
    private LocalDateTime timestamp;

    public TransactionHistoryDTO(Long userId, String userName, String cryptoType, String transactionType, double amount, double price, LocalDateTime timestamp) {
        this.userId = userId;
        this.userName = userName;
        this.cryptoType = cryptoType;
        this.transactionType = transactionType;
        this.amount = amount;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCryptoType() {
        return cryptoType;
    }

    public void setCryptoType(String cryptoType) {
        this.cryptoType = cryptoType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
