package com.techtest.cryptodemo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "WALLET_TBL")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users users;

    private double usdtBalance = 50000.0;
    private double btcValue = 0.0;
    private double ethValue = 0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public double getUsdtBalance() {
        return usdtBalance;
    }

    public void setUsdtBalance(double usdtBalance) {
        this.usdtBalance = usdtBalance;
    }

    public double getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(double btcValue) {
        this.btcValue = btcValue;
    }

    public double getEthValue() {
        return ethValue;
    }

    public void setEthValue(double ethValue) {
        this.ethValue = ethValue;
    }
}
