package com.techtest.cryptodemo.service.impl;

import com.techtest.cryptodemo.common.TradeConst;
import com.techtest.cryptodemo.entities.Price;
import com.techtest.cryptodemo.repositories.PriceRepository;
import com.techtest.cryptodemo.service.PriceProcSchedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PriceProcSchedServiceImpl implements PriceProcSchedService{

    private static final Logger log = LoggerFactory.getLogger(PriceProcSchedServiceImpl.class);

    private PriceRepository priceRepository;
    private RestTemplate restTemplate;

    @Autowired
    public PriceProcSchedServiceImpl(PriceRepository priceRepository, RestTemplate restTemplate){
        this.priceRepository = priceRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 10000)
    @Override
    public void processPrices() {
        log.info("Entered processPrices() ");
        double binanceBtcAsk = fetchPriceBinance(TradeConst.BITCOIN_CONST, TradeConst.ASK_PRICE);
        double binanceEthAsk = fetchPriceBinance(TradeConst.ETHEREUM_CONST, TradeConst.ASK_PRICE);
        double binanceEthBid = fetchPriceBinance(TradeConst.ETHEREUM_CONST, TradeConst.BID_PRICE);
        double binanceBtcBid = fetchPriceBinance(TradeConst.BITCOIN_CONST, TradeConst.BID_PRICE);

        double huobiBtcAsk = fetchPriceHoubi(TradeConst.BITCOIN_CONST, TradeConst.ASK_PRICE);
        double huobiEthAsk = fetchPriceHoubi(TradeConst.ETHEREUM_CONST, TradeConst.ASK_PRICE);
        double huobiBtcBid = fetchPriceHoubi(TradeConst.BITCOIN_CONST, TradeConst.BID_PRICE);
        double huobiEthBid = fetchPriceHoubi(TradeConst.ETHEREUM_CONST, TradeConst.BID_PRICE);

        // Best ask (lowest) for buying
        double bestBtcAsk = Math.min(binanceBtcAsk, huobiBtcAsk);
        double bestBtcBid = Math.max(binanceBtcBid, huobiBtcBid);
        double bestEthAsk = Math.min(binanceEthAsk, huobiEthAsk);
        double bestEthBid = Math.max(binanceEthBid, huobiEthBid);

        savePrice(TradeConst.BITCOIN_CONST, bestBtcBid, bestBtcAsk);
        savePrice(TradeConst.ETHEREUM_CONST, bestEthBid, bestEthAsk);
        log.info("Exited processPrice()");
    }

    @Override
    public double fetchPriceBinance(String cryptoType, String priceType) {
        try{
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(TradeConst.BINANCE_API_URL + cryptoType,
                    HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});

            Map<String, Object> responseBody = response.getBody();
            if(responseBody != null){
                if(TradeConst.ASK_PRICE.equalsIgnoreCase(priceType)){
                    return Double.parseDouble(responseBody.get(TradeConst.ASK_PRICE).toString());
                }else if(TradeConst.BID_PRICE.equalsIgnoreCase(priceType)){
                    return Double.parseDouble(responseBody.get(TradeConst.BID_PRICE).toString());
                }
            }

        }catch(Exception e){
            log.info("Error while fetching price from binance url: {}", e.getMessage());

        }
        return 0.0;
    }

    @Override
    public double fetchPriceHoubi(String cryptoType, String priceType) {
        try{
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(TradeConst.HOUBI_API_URL,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Map<String, Object>>() {});

            Map<String, Object> responseBody = response.getBody();
            if(responseBody != null && responseBody.containsKey("data")){
                List<Map<String,Object>> tickers = (List<Map<String,Object>>) responseBody.get("data");

                for(Map<String,Object> ticker: tickers){
                    if(cryptoType.equalsIgnoreCase(ticker.get("symbol").toString())){
                        if(TradeConst.ASK_PRICE.equalsIgnoreCase(priceType)){
                            return Double.parseDouble(ticker.get("ask").toString());
                        }else if(TradeConst.BID_PRICE.equalsIgnoreCase(priceType)){
                            return Double.parseDouble(ticker.get("bid").toString());
                        }
                    }
                }
            }


        }catch(Exception e){
            log.info("Error while fetching price from houbi url: {}", e.getMessage());
        }
        return 0.0;
    }

    @Override
    public void savePrice(String cryptoType, double askPrice, double bidPrice) {
        log.info("Enter savePrice()");
        Price price = new Price();
        price.setCryptoType(cryptoType);
        price.setAskPrice(askPrice);
        price.setBidPrice(bidPrice);
        price.setTimestamp(LocalDateTime.now());
        priceRepository.save(price);
        log.info("Price saved");
        log.info("Exiting savePrice()");

    }
}
