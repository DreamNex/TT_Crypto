package com.techtest.cryptodemo.service;

public interface PriceProcSchedService {

    void processPrices();
    double fetchPriceBinance(String cryptoType, String priceType);
    double fetchPriceHoubi(String cryptoType, String priceType);
    void savePrice(String cryptoType, double askPrice, double bidPrice);
}
