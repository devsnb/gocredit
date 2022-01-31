package com.gocredit;

import com.gocredit.model.*;
import com.gocredit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GocreditBillServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GocreditBillServiceApplication.class, args);
    }

    @Autowired
    IBillService billService;

    @Autowired
    ICreditCardService creditCardService;

    @Autowired
    IUserService userService;

    @Autowired
    IPaymentService paymentService;

    @Override
    public void run(String... args) throws Exception {

        Address address = new Address("69A", "Ohm Shakti Nagar", "Coimbatore", "TamilNadu", 651668);
        User user = new User("Saranya", LocalDate.parse("1999-03-27"), "saranya@gmail.com", "saran", 9876543210L, Role.USER, address, new HashSet<>());
        ResponseEntity<User> userResponseEntity = userService.signup(user);
        User savedUser = userResponseEntity.getBody();

        CreditCard card1 = new CreditCard();
        Bill bill = new Bill("One97Communications", LocalDate.now(), 3650, false, null, null);

        CreditCard card = new CreditCard("Snehnagshu", "1234098765456789", CardType.MASTERCARD, LocalDate.parse("2025-10-31"), new HashSet<>(Arrays.asList(bill)), savedUser);

        ResponseEntity<CreditCard> creditCardResponseEntity = creditCardService.addCard(card, 1);

        CreditCard savedCard = creditCardResponseEntity.getBody();

        Bill bill1 = new Bill("Me", LocalDate.now(), 562, false, savedCard, null);

        Bill savedBill = billService.addBill(bill1);

//        Payment payment = paymentService.addPayment(savedBill, new Payment());


    }
}
