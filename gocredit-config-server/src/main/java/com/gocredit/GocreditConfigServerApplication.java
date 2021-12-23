package com.gocredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GocreditConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GocreditConfigServerApplication.class, args);
	}

}
