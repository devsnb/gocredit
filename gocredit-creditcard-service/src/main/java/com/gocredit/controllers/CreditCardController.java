package com.gocredit.controllers;

import com.gocredit.model.CreditCard;
import com.gocredit.model.User;
import com.gocredit.service.ICreditCardService;
import com.gocredit.service.IUserService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("credit-card-api")
public class CreditCardController {
    @Autowired
    ICreditCardService creditCardService;

    @Autowired
    IUserService userService;

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    /**
     * Adds new credit card in the database
     *
     * @param card CreditCard is a creditCard object in the database
     * @return returns the newly created user object from the database
     */
    @PostMapping("/cards/user/{userId}")
    public ResponseEntity<CreditCard> addCard(@RequestBody CreditCard card, @PathVariable("userId") int userId) {
        logger.info("POST /credit-card-api/cards");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside addCard Method");
        CreditCard creditCard = creditCardService.addCard(userId, card);
        logger.debug("creditCardService.addCard called");
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCard);
    }

    /**
     * Updates a credit card in the database
     *
     * @param card CreditCard object with id
     * @return Returns the updated CreditCard from the database
     */
    @PutMapping("/cards")
    public ResponseEntity<CreditCard> updateCard(@RequestBody CreditCard card) {
        logger.info("PUT /credit-card-api/cards");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside updateCard Method");
        CreditCard creditCard = creditCardService.updateCard(card);
        logger.debug("creditCardService.updateCard called");
        return ResponseEntity.status(HttpStatus.OK).body(creditCard);
    }

    /**
     * Delete a single creditCard id in the database based on the card id provided
     *
     * @param cardId CreditCard id to delete the creditCard in the database
     * @return Returns the deleted card from the database
     */
    @DeleteMapping("/cards/id/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable("cardId") int cardId) {
        logger.info("DELETE /credit-card-api/cards");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside deleteCard Method");
        creditCardService.deleteCard(cardId);
        logger.debug("creditCardService.deleteCard called");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Finds creditCard based on userId and nameOnCard provided
     *
     * @param userId     userId of the user in the database
     * @param nameOnCard nameOnCard of credit card in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("/cards/userId/{userId}/nameOnCard/{nameOnCard}")
    public ResponseEntity<List<CreditCard>> getByUserAndNameOnCard(@PathVariable("userId") int userId, @PathVariable("nameOnCard") String nameOnCard) {
        logger.info("GET /credit-card-api/cards/userId/{userId}/nameOnCard/{nameOnCard}");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside getByUserAndNameOnCard Method");
        List<CreditCard> creditCards = creditCardService.getByUserAndNameOnCard(userId, nameOnCard);
        logger.debug("creditCardService.getByUserAndNameOnCard called");
        return ResponseEntity.ok().body(creditCards);
    }

    /**
     * Finds creditCard based on userId and type provided
     *
     * @param userId   userId of the user in the database
     * @param cardType type of credit card in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("/cards/userId/{userId}/type/{type}")
    public ResponseEntity<List<CreditCard>> getByUserAndType(@PathVariable("userId") int userId, @PathVariable("type") String cardType) {
        logger.info("GET /credit-card-api/cards/userId/{userId}/type/{type}");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside getByUserAndType Method");
        List<CreditCard> creditCards = creditCardService.getByUserAndType(userId, cardType);
        logger.debug("creditCardService.getByUserAndType called");
        return ResponseEntity.ok().body(creditCards);
    }

    /**
     * Finds creditCard based on userId and Number provided
     *
     * @param number card number of the credit card in the database
     * @return Returns a credit card found in the database
     */
    @GetMapping("/cards/number/{number}")
    public ResponseEntity<CreditCard> getByCardNumber(@PathVariable("number") String number) {
        logger.info("GET /credit-card-api/cards/number/{number}");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside getByCardNumber Method");
        CreditCard creditCards = creditCardService.getByCardNumber(number);
        logger.debug("creditCardService.getByCardNumber called");
        return ResponseEntity.ok().body(creditCards);
    }

    /**
     * Finds creditCard based on userId provided
     *
     * @param userId userId of the user in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("/cards/userId/{userId}")
    public ResponseEntity<List<CreditCard>> getByUserId(@PathVariable("userId") int userId) {
        logger.info("GET /credit-card-api/cards/userId/{userId}");
        logger.debug("Inside CreditCard Controller");
        logger.debug("Inside getByUserId Method");
        List<CreditCard> creditCards = creditCardService.getByUserId(userId);
        logger.debug("creditCardService.getByUserId called");
        return ResponseEntity.ok().body(creditCards);
    }

}