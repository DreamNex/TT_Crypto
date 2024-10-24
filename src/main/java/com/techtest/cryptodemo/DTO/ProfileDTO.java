package com.techtest.cryptodemo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProfileDTO {

    @JsonIgnore
    private Long userId;
    private String userName;
    private double usdtBalance;
    private double ethAmount;
    private double btcAmount;

    public ProfileDTO(Long userId, String userName, double usdtBalance, double ethAmount, double btcAmount)
    {
        this.userId = userId;
        this.userName = userName;
        this.usdtBalance = usdtBalance;
        this.ethAmount = ethAmount;
        this.btcAmount = btcAmount;
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

    public double getUsdtBalance() {
        return usdtBalance;
    }

    public void setUsdtBalance(double usdtBalance) {
        this.usdtBalance = usdtBalance;
    }

    public double getEthAmount() {
        return ethAmount;
    }

    public void setEthAmount(double ethAmount) {
        this.ethAmount = ethAmount;
    }

    public double getBtcAmount() {
        return btcAmount;
    }

    public void setBtcAmount(double btcAmount) {
        this.btcAmount = btcAmount;
    }
}
