package com.gocredit.service;

import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.model.CardType;
import com.gocredit.model.CreditCard;
import com.gocredit.repository.ICreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements ICreditCardService {


    ICreditCardRepository creditCardRepository;

    @Autowired
    public void setCreditCardRepository(ICreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    /**
     * Adds new creditCard in the database
     *
     * @param card is a creditCard object in the database
     * @return returns the newly created creditCard object from the database
     */
    @Override
    public CreditCard addCard(CreditCard card) {
        return creditCardRepository.save(card);
    }

    /**
     * Updates a creditCard in the database
     *
     * @param card CreditCard object with id
     */
    @Override
    public void updateCard(CreditCard card) {
        creditCardRepository.save(card);
    }

    /**
     * Delete a single creditCard id in the database
     *
     * @param cardId Credit card id to delete the creditCard in the database
     */
    @Override
    public void deleteCard(int cardId) {
        creditCardRepository.deleteById(cardId);
    }

    /**
     * Finds creditCards based on userId and nameOnCard provided
     *
     * @param userId     id of the user in the database
     * @param nameOnCard name on creditCard in the database
     * @return Returns a list of creditCards found in the database
     * @throws CreditCardNotFoundException If no creditCard found in the database
     */
    @Override
    public List<CreditCard> getByUserAndNameOnCard(int userId, String nameOnCard) throws CreditCardNotFoundException {
        List<CreditCard> creditCards = creditCardRepository.findByUserAndNameOnCard(userId, nameOnCard);
        if (creditCards.isEmpty()) {
            throw new CreditCardNotFoundException("No creditCard found with the userId of " + userId + "and nameOnCard of " + nameOnCard);
        }
        return creditCards;
    }

    /**
     * Finds creditCards based on userId and type of card provided
     *
     * @param userId   id of the user in the database
     * @param cardType cardType of the creditCard in the database
     * @return Returns a list of creditCards found in the database
     * @throws CreditCardNotFoundException If no creditCard found in the database
     */
    @Override
    public List<CreditCard> getByUserAndType(int userId, String cardType) throws CreditCardNotFoundException {
        List<CreditCard> creditCards = creditCardRepository.findByUserAndType(userId, CardType.valueOf(cardType.toUpperCase()));
        if (creditCards.isEmpty()) {
            throw new CreditCardNotFoundException("No creditCard found with the userId of " + userId + "and cardType of " + cardType);
        }
        return creditCards;
    }

    /**
     * Finds creditCards based on card number provided
     *
     * @param number cardNumber of the creditCard in the database
     * @return Returns a list of creditCards found in the database
     * @throws CreditCardNotFoundException If no creditCard found in the database
     */
    @Override
    public CreditCard getByCardNumber(String number) throws CreditCardNotFoundException {
        CreditCard creditCards = creditCardRepository.findByCardNumber(number).stream()
                .findFirst().orElseThrow(() -> new CreditCardNotFoundException("No creditCard found with the credit card number" + number));

        return creditCards;
    }

    /**
     * Finds creditCards based on user id provided
     *
     * @param userId User id to find the user in the database
     * @return Returns a list of creditCards found in the database
     * @throws CreditCardNotFoundException If no creditCard found in the database
     */
    @Override
    public List<CreditCard> getByUserId(int userId) throws CreditCardNotFoundException {
        List<CreditCard> creditCards = creditCardRepository.findByUserId(userId);
        if (creditCards.isEmpty()) {
            throw new CreditCardNotFoundException("No creditCard found with the userId of " + userId);
        }
        return creditCards;
    }
}
