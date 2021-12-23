package com.gocredit.service;

import com.gocredit.exceptions.CardNotFoundException;
import com.gocredit.model.CreditCard;

import java.util.List;

public interface ICreditCardService {


    CreditCard addCard(CreditCard card);
    void updateCard(CreditCard card);
    void deleteCard(int cardId);


    CreditCard getById(int cardId) throws CardNotFoundException;
    List<CreditCard> getAll();
    List<CreditCard> getByName(String name) throws CardNotFoundException;
   

}
