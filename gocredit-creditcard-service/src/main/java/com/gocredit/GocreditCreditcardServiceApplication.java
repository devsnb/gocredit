package com.gocredit;

import com.gocredit.model.*;
import com.gocredit.service.ICreditCardService;
import com.gocredit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GocreditCreditcardServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GocreditCreditcardServiceApplication.class, args);
	}
	@Autowired
	ICreditCardService creditCardService;

	@Autowired
	IUserService userService;

	@Override
	public void run(String... args) throws Exception {

		Address address = new Address();
		address.setCity("Hyderabad");
		address.setDoorNo("D25");
		address.setZipcode(500008);
		address.setStreetName("Silicon Valley Road");
		address.setState("Telangana");

        User user= new User();
		user.setName("Thanmai");
		user.setDateOfBirth(LocalDate.parse("1999-03-06"));
		user.setEmail("thanmai@gmail.com");
		user.setContactNumber(8954536872L);
		user.setPassword("thanmai");
		user.setRole(Role.ADMIN);
		user.setAddress(address);

//		ResponseEntity<User> userResponse = userService.signup(user);
//
//		User user1 = userResponse.getBody();
//
//		CreditCard creditCard = new CreditCard("Thanmai","8740973628641906", CardType.VISA, LocalDate.parse("2024-08-31"),new HashSet<>(), user1);
//
//		CreditCard savedCard = creditCardService.addCard(1, creditCard);
//
//		System.out.println(creditCardService.getByUserId(1));
////		System.out.println(savedCard);
//
//		System.out.println(creditCardService.getByCardNumber("8740973628641906"));
//		System.out.println("Hello");


	}
}
