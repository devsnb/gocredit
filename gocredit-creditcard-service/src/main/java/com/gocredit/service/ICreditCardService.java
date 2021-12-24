package com.gocredit.service;

import com.gocredit.exceptions.CardNotFoundException;
import com.gocredit.model.CreditCard;

import java.util.List;

public interface ICreditCardService {

    CreditCard addCard(CreditCard card);

    void updateCard(CreditCard card);

    void deleteCard(int cardId);

    List<CreditCard> getByUserAndNameOnCard(int userId, String nameOnCard) throws CardNotFoundException;

    List<CreditCard> getByUserAndType(int userId, String cardType) throws CardNotFoundException;

    CreditCard getByCardNumber(String number) throws CardNotFoundException;

    List<CreditCard> getByUserId(int userId) throws CardNotFoundException;

}
