package com.gocredit.service;

import com.gocredit.model.CreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "CREDITCARD-SERVICE")
public interface ICreditCardService {
    /**
     * Adds new credit card in the database
     *
     * @param card CreditCard is a creditCard object in the database
     * @return returns the newly created user object from the database
     */
    @PostMapping("credit-card-api/cards/user/{userId}")
    public ResponseEntity<CreditCard> addCard(@RequestBody CreditCard card, @PathVariable("userId") int userId);

    /**
     * Updates a credit card in the database
     *
     * @param card CreditCard object with id
     * @return Returns the updated CreditCard from the database
     */
    @PutMapping("credit-card-api/cards")
    public ResponseEntity<CreditCard> updateCard(@RequestBody CreditCard card);

    /**
     * Delete a single creditCard id in the database based on the card id provided
     *
     * @param cardId CreditCard id to delete the creditCard in the database
     * @return Returns the deleted card from the database
     */
    @DeleteMapping("credit-card-api/cards/id/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable("cardId") int cardId);

    /**
     * Finds creditCard based on userId and nameOnCard provided
     *
     * @param userId     userId of the user in the database
     * @param nameOnCard nameOnCard of credit card in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("credit-card-api/cards/userId/{userId}/nameOnCard/{nameOnCard}")
    public ResponseEntity<List<CreditCard>> getByUserAndNameOnCard(@PathVariable("userId") int userId, @PathVariable("nameOnCard") String nameOnCard);

    /**
     * Finds creditCard based on userId and type provided
     *
     * @param userId   userId of the user in the database
     * @param cardType type of credit card in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("credit-card-api/cards/userId/{userId}/type/{type}")
    public ResponseEntity<List<CreditCard>> getByUserAndType(@PathVariable("userId") int userId, @PathVariable("type") String cardType);

    /**
     * Finds creditCard based on userId and Number provided
     *
     * @param number card number of the credit card in the database
     * @return Returns a credit card found in the database
     */
    @GetMapping("credit-card-api/cards/number/{number}")
    public ResponseEntity<CreditCard> getByCardNumber(@PathVariable("number") String number);

    /**
     * Finds creditCard based on userId provided
     *
     * @param userId userId of the user in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("credit-card-api/cards/userId/{userId}")
    public ResponseEntity<List<CreditCard>> getByUserId(@PathVariable("userId") int userId);

}
