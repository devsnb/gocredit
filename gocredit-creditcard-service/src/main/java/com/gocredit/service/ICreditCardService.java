package com.gocredit.service;

import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.model.CreditCard;

import java.util.List;

public interface ICreditCardService {

    CreditCard addCard(CreditCard card);

    void updateCard(CreditCard card);

    void deleteCard(int cardId);

    List<CreditCard> getByUserAndNameOnCard(int userId, String nameOnCard) throws CreditCardNotFoundException;

    List<CreditCard> getByUserAndType(int userId, String cardType) throws CreditCardNotFoundException;

    CreditCard getByCardNumber(String number) throws CreditCardNotFoundException;

    List<CreditCard> getByUserId(int userId) throws CreditCardNotFoundException;

}
