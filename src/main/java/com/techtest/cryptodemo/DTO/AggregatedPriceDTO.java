package com.techtest.cryptodemo.DTO;



public class AggregatedPriceDTO {

    private String cryptoType;
    private double bestBidPrice;
    private double bestAskPrice;

    public AggregatedPriceDTO(String cryptoType, double bestBidPrice, double bestAskPrice){
        this.cryptoType=cryptoType;
        this.bestAskPrice=bestAskPrice;
        this.bestBidPrice=bestBidPrice;
    }

    public String getCryptoType() {
        return cryptoType;
    }

    public void setCryptoType(String cryptoType) {
        this.cryptoType = cryptoType;
    }

    public double getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(double bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public double getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(double bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }
}
