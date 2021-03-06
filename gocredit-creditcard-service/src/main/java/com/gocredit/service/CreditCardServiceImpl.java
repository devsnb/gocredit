package com.gocredit.service;

import com.gocredit.exceptions.CreditCardAlreadyExistsException;
import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.CardType;
import com.gocredit.model.CreditCard;
import com.gocredit.model.User;
import com.gocredit.repository.ICreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

    @Autowired
    private ICreditCardRepository creditCardRepository;

    @Autowired
    private IUserService userService;

    /**
     * Adds new creditCard in the database
     *
     * @param card is a creditCard object in the database
     * @return Returns the newly created creditCard object from the database
     */
    @Override
    public CreditCard addCard(int userId, CreditCard card) throws UserNotFoundException, CreditCardAlreadyExistsException {
        CreditCard card1 = null;

        card1 = creditCardRepository.findByCardNumber(card.getCardNumber());

        if (card1 != null) {
            throw new CreditCardAlreadyExistsException("This card already exists");
        }

        ResponseEntity<User> userResponse = userService.getById(userId);
        User user = userResponse.getBody();
        if (user == null) {
            throw new UserNotFoundException("No user not found with the id of " + userId);
        }
        card.setUser(user);

        return creditCardRepository.save(card);
    }

    /**
     * Updates a creditCard in the database
     *
     * @param card CreditCard object with id
     */
    @Override
    public CreditCard updateCard(CreditCard card) {

        CreditCard card1 = creditCardRepository.findByCardNumber(card.getCardNumber());

        if (card1 == null) {
            throw new CreditCardNotFoundException("No card found with the credit card number of " + card.getCardNumber());
        }

        return creditCardRepository.save(card);

    }

    /**
     * Delete a single creditCard in the database
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
     * @return Returns a creditCard found in the database
     * @throws CreditCardNotFoundException If no creditCard found in the database
     */
    @Override
    public CreditCard getByCardNumber(String number) throws CreditCardNotFoundException {
        CreditCard card = null;
        card = creditCardRepository.findByCardNumber(number);

        if (card == null) {
            throw new CreditCardNotFoundException("No creditCard found with the credit card number " + number);
        }

        return card;
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

    @Override
    public CreditCard getById(int cardid) throws CreditCardNotFoundException {
        return creditCardRepository.findById(cardid).orElseThrow(() -> new CreditCardNotFoundException("No credit card found with the id of " + cardid));
    }
}
