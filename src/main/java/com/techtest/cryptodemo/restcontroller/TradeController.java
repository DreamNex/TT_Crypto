package com.techtest.cryptodemo.restcontroller;

import com.techtest.cryptodemo.service.impl.TradeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/trade")
public class TradeController {

    private static final Logger log = LoggerFactory.getLogger(TradeController.class);
    private TradeServiceImpl tradeService;

    @Autowired
    public TradeController(TradeServiceImpl tradeService){
        log.info("Creating dependencies..");
        this.tradeService = tradeService;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyCrypto(@RequestParam String cryptoType, @RequestParam double amount, @RequestParam Long userId){
        return ResponseEntity.ok(tradeService.buyCrypto(cryptoType,amount,userId));
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellCrypto(@RequestParam String cryptoType, @RequestParam double amount, @RequestParam Long userId){
        return ResponseEntity.ok(tradeService.sellCrypto(cryptoType,amount,userId));
    }
}
