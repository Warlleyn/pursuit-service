package com.example.pursuitservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class PursuitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PursuitServiceApplication.class, args);
	}

}
