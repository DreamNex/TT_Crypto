package com.techtest.cryptodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CryptodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptodemoApplication.class, args);
	}

}
