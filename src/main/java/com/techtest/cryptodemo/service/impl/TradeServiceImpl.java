package com.techtest.cryptodemo.service.impl;

import com.techtest.cryptodemo.DTO.AggregatedPriceDTO;
import com.techtest.cryptodemo.common.TradeConst;
import com.techtest.cryptodemo.entities.Price;
import com.techtest.cryptodemo.entities.Transaction;
import com.techtest.cryptodemo.entities.Users;
import com.techtest.cryptodemo.entities.Wallet;
import com.techtest.cryptodemo.repositories.PriceRepository;
import com.techtest.cryptodemo.repositories.TransactionRepository;
import com.techtest.cryptodemo.repositories.UserRepository;
import com.techtest.cryptodemo.repositories.WalletRepository;
import com.techtest.cryptodemo.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    private WalletRepository walletRepository;
    private TransactionRepository transactionRepository;
    private PriceRepository priceRepository;
    private UserRepository userRepository;

    @Autowired
    public TradeServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository, PriceRepository priceRepository, UserRepository userRepository){
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.priceRepository = priceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String buyCrypto(String cryptoType, double amount, Long userId) {
        log.info("Entered buyCrypto method");
        Wallet wallet = walletRepository.findById(userId).orElseThrow();
        Price currentPrice = priceRepository.findTopByCryptoTypeOrderByTimestampDesc(cryptoType);

        if(TradeConst.ETHEREUM_CONST.equalsIgnoreCase(cryptoType)){
            double totalCost = currentPrice.getAskPrice() * amount;
            if(wallet.getUsdtBalance()>= totalCost){
                wallet.setEthValue(wallet.getEthValue() + amount);
                wallet.setUsdtBalance(wallet.getUsdtBalance()-totalCost);
                recordTransaction(userId,cryptoType,"BUY", amount,currentPrice.getAskPrice());
                walletRepository.save(wallet);
                log.info("Exit buyCrypto method");
                return "Purchase successful";

            }

        }else if (TradeConst.BITCOIN_CONST.equalsIgnoreCase(cryptoType)) {
            double totalCost = currentPrice.getAskPrice() * amount;
            if (wallet.getUsdtBalance() >= totalCost) {
                wallet.setEthValue(wallet.getEthValue() + amount);
                wallet.setUsdtBalance(wallet.getUsdtBalance() - totalCost);
                recordTransaction(userId, cryptoType, "BUY", amount, currentPrice.getAskPrice());
                walletRepository.save(wallet);
                log.info("Exit buyCrypto method");
                return "Purchase successful";
            }
        }
        log.info("Exit buyCrypto method");
        return "Insufficient funds to purchase";
    }

    @Override
    public String sellCrypto(String cryptoType, double amount, Long userId) {
        log.info("Entered sellCrypto method");
        Wallet wallet = walletRepository.findById(userId).orElseThrow();
        Price currentPrice = priceRepository.findTopByCryptoTypeOrderByTimestampDesc(cryptoType);

        if(TradeConst.ETHEREUM_CONST.equalsIgnoreCase(cryptoType) && wallet.getEthValue() >= amount){
            double totalProfit = currentPrice.getAskPrice() * amount;
            if(wallet.getUsdtBalance()>= totalProfit){
                wallet.setEthValue(wallet.getEthValue() - amount);
                wallet.setUsdtBalance(wallet.getUsdtBalance()+totalProfit);
                recordTransaction(userId,cryptoType,"SELL", amount,currentPrice.getAskPrice());
                walletRepository.save(wallet);
                log.info("Exit sellCrypto method");
                return "Sold ETH successful";
            }

        }else if (TradeConst.BITCOIN_CONST.equalsIgnoreCase(cryptoType)) {
            double totalProfit = currentPrice.getAskPrice() * amount;
            if (wallet.getUsdtBalance() >= totalProfit) {
                wallet.setEthValue(wallet.getEthValue() - amount);
                wallet.setUsdtBalance(wallet.getUsdtBalance() + totalProfit);
                recordTransaction(userId, cryptoType, "SELL", amount, currentPrice.getAskPrice());
                walletRepository.save(wallet);
                log.info("Exit sellCrypto method");
                return "Sold BIT successful";
            }
        }

        log.info("Exit sellCrypto method");
        return "Insufficient cryto to sell";
    }

    @Override
    public void recordTransaction(Long userId, String cryptoType, String transactionType, double amount, double price) {
        log.info("Entered recordTransaction method");
        Users uuser = userRepository.findById(userId).orElseThrow();
        Transaction transaction = new Transaction();
        transaction.setUser(uuser);
        transaction.setCryptoType(cryptoType);
        transaction.setAmount(amount);
        transaction.setPrice(price);
        transaction.setTransactionType(transactionType);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        log.info("Exit recordTransaction method");


    }

    @Override
    public AggregatedPriceDTO getLatestAgPrice(String cryptoType) {

        double bestBidPrice = 0.0;
        double bestAskPrice = 0.0;

        Price latestPrice = priceRepository.findTopByCryptoTypeOrderByTimestampDesc(cryptoType);

        if(latestPrice != null){
            bestAskPrice = latestPrice.getAskPrice();
            bestBidPrice = latestPrice.getBidPrice();
        }

        return new AggregatedPriceDTO(cryptoType,bestBidPrice,bestAskPrice);
    }
}
