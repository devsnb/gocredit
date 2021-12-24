package com.gocredit.service;

import com.gocredit.exceptions.CardNotFoundException;
import com.gocredit.model.CreditCard;
import com.gocredit.repository.ICreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements ICreditCardService{


    ICreditCardRepository creditCardRepository;
    @Autowired
    public void setCreditCardRepository(ICreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard addCard(CreditCard card) {
        return creditCardRepository.save(card);
    }

    @Override
    public void updateCard(CreditCard card) {
       creditCardRepository.save(card);
    }

    @Override
    public void deleteCard(int cardId) {
        creditCardRepository.deleteById(cardId);
    }

    @Override
    public List<CreditCard> getByUserAndNameOnCard(int userId, String nameOnCard) throws CardNotFoundException {
        return null;
    }

    @Override
    public List<CreditCard> getByUserAndType(int userId, String cardType) throws CardNotFoundException {
        return null;
    }

    @Override
    public CreditCard getByCardNumber(String number) throws CardNotFoundException {
        return null;
    }

    @Override
    public List<CreditCard> getByUserId(int userId) throws CardNotFoundException {
        return null;
    }
}
