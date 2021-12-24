package com.gocredit.controllers;


import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.model.CreditCard;
import com.gocredit.service.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("credit-card-api")
public class CreditCardController {

    ICreditCardService creditCardService;

    @Autowired
    public void setCreditCardService(ICreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public ResponseEntity<List<CreditCard>> getByUserAndNameOnCard(int userId, String nameOnCard) throws CreditCardNotFoundException {
        return null;
    }

    List<CreditCard> getByUserAndType(int userId, String cardType) throws CreditCardNotFoundException {
        return null;
    }

    CreditCard getByCardNumber(String number) throws CreditCardNotFoundException {
        return null;
    }

    List<CreditCard> getByUserId(int userId) throws CreditCardNotFoundException {
        return null;
    }



}
