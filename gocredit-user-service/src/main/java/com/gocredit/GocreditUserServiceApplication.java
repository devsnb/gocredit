package com.gocredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class GocreditUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GocreditUserServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
