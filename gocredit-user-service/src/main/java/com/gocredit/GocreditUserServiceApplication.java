package com.gocredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GocreditUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GocreditUserServiceApplication.class, args);
	}

}
