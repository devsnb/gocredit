package com.gocredit.service;

import com.gocredit.model.CreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CREDITCARD-SERVICE")
public interface ICreditCardService {

    @PostMapping("/credit-card-api/cards/user/{userId}")
    public ResponseEntity<CreditCard> addCard(@RequestBody CreditCard card, @PathVariable("userId") int userId);

    @GetMapping("/credit-card-api/cards/number/{number}")
    public ResponseEntity<CreditCard> getByCardNumber(@PathVariable("number") String number);
}
