package com.gocredit;

import com.gocredit.model.*;
import com.gocredit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootApplication
@EnableEurekaClient
public class GocreditUserServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GocreditUserServiceApplication.class, args);
	}

	@Autowired
	IUserService userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Override
	public void run(String... args) throws Exception {
		Address address = new Address("69A","Ohm Shakti Nagar","Coimbatore","TamilNadu",651668);
		User user = new User("Saranya", LocalDate.parse("1999-03-27"),"saranya@gmail.com","saran",9876543210l, Role.USER,address, new HashSet<>());
		System.out.println("Signup : ");
		userService.signup(user);

		System.out.println();
		System.out.println(userService.loginWithEmail("saranya@gmail.com", "saran"));

		System.out.println();
		System.out.println(userService.loginWithContactNumber(9876543210l, "saran"));

		System.out.println();
		System.out.println("Get All");
		userService.getAll().forEach(System.out::println);

		System.out.println();
		System.out.println("Get By Role");
		userService.getByRole("user").forEach(System.out::println);

		System.out.println();
		System.out.println("Get By Name");
		userService.getByName("Saranya").forEach(System.out::println);

	}
}
