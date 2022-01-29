package com.gocredit.controllers;

import com.gocredit.model.CreditCard;
import com.gocredit.model.User;
import com.gocredit.service.ICreditCardService;
import com.gocredit.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("user-api")
@Profile("dev")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICreditCardService cardService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Adds new user in the database
     *
     * @param user User is a user object in the database
     * @return Returns the newly created user object from the database
     */
    @PostMapping("/users")
    public ResponseEntity<User> signup(@RequestBody User user) {
        logger.info("POST /user-api/users");
        logger.debug("Inside User Controller");
        logger.debug("Inside signup Method");
        User createUser = userService.signup(user);
        logger.debug("userService.signupUser Called");
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @PostMapping("/users/{userId}/password/{password}/{new}")
    public ResponseEntity<Void> changePassword(@PathVariable("userId") int userId,
                                               @PathVariable("password") String password,
                                               @PathVariable("new") String newPassword) {
        userService.changePassword(userId, password, newPassword);
        return ResponseEntity.ok().build();
    }

    /**
     * Login the user with provided email and password
     *
     * @param email    email associated with the user account
     * @param password password associated with the account
     * @return Returns already registered user with provided email or sends a bad request
     */
    @PostMapping("/users/login/email/{email}/password/{password}")
    public ResponseEntity<User> loginWithEmail(@PathVariable("email") String email, @PathVariable("password") String password) {
        User user = userService.loginWithEmail(email, password);

        return ResponseEntity.ok(user);
    }

    /**
     * Login the user with provided contact number and password
     *
     * @param contact  contact number associated with the user account
     * @param password password associated with the account
     * @return Returns already registered user with provided contact number or sends a bad request
     */
    @PostMapping("/users/login/contact/{contact}/password/{password}")
    public ResponseEntity<User> loginWithContactNumber(@PathVariable("contact") long contact, @PathVariable("password") String password) {

        User user = userService.loginWithContactNumber(contact, password);

        return ResponseEntity.ok(user);
    }


    /**
     * Updates a user in the database
     *
     * @param user User object with id
     * @return Returns the updated User from the database
     */
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        logger.info("POST /user-api/users");
        logger.debug("Inside User Controller");
        logger.debug("Inside updateUser Method");
        User updateUser = userService.updateUser(user);
        logger.debug("userService.updateUser Called");
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    /**
     * Delete a single user id in the database based on the user id provided
     *
     * @param userId User id to delete the user in the database
     * @return Returns the deleted user from the database
     */
    @DeleteMapping("/users/id/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        logger.info("DELETE /user-api/users/id/{userId}");
        logger.debug("Inside User Controller");
        logger.debug("Inside deleteUser Method");
        userService.deleteUser(userId);
        logger.debug("userService.deleteUser Called");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Finds a single user id  from the database based on the user id provided
     *
     * @param userId User id to find the user in the database
     * @return Returns the User found in the database
     */
    @GetMapping("/users/id/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") int userId) {
        logger.info("GET /user-api/users/id/{userId}");
        logger.debug("Inside user Controller");
        logger.debug("Inside getById Method");
        User user = userService.getById(userId);
        logger.debug("userService.getById Called");
        return ResponseEntity.ok().body(user);
    }

    /**
     * Adds new credit card in the database
     *
     * @param card CreditCard is a creditCard object in the database
     * @return returns the newly created user object from the database
     */
    @PostMapping("/users/id/{userId}/cards")
    public ResponseEntity<CreditCard> addCreditCard(@PathVariable("userId") int userId, @RequestBody CreditCard card) {
        return cardService.addCard(card, userId);
    }

    /**
     * Updates a credit card in the database
     *
     * @param card CreditCard object with id
     * @return Returns the updated CreditCard from the database
     */
    @PutMapping("/users/cards")
    public ResponseEntity<CreditCard> updateCard(@RequestBody CreditCard card) {
        return cardService.updateCard(card);
    }

    /**
     * Delete a single creditCard id in the database based on the card id provided
     *
     * @param cardId CreditCard id to delete the creditCard in the database
     * @return Returns the deleted card from the database
     */
    @DeleteMapping("users/cards/id/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable("cardId") int cardId) {
        return cardService.deleteCard(cardId);
    }

    /**
     * Finds creditCard based on userId and nameOnCard provided
     *
     * @param userId     userId of the user in the database
     * @param nameOnCard nameOnCard of credit card in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("users/{userId}/cards/nameOnCard/{nameOnCard}")
    public ResponseEntity<List<CreditCard>> getByUserAndNameOnCard(@PathVariable("userId") int userId, @PathVariable("nameOnCard") String nameOnCard) {
        return cardService.getByUserAndNameOnCard(userId, nameOnCard);
    }

    /**
     * Finds creditCard based on userId and type provided
     *
     * @param userId   userId of the user in the database
     * @param cardType type of credit card in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("users/{userId}/cards/type/{type}")
    public ResponseEntity<List<CreditCard>> getByUserAndType(@PathVariable("userId") int userId, @PathVariable("type") String cardType) {
        return cardService.getByUserAndType(userId, cardType);
    }

    /**
     * Finds creditCard based on userId and Number provided
     *
     * @param number card number of the credit card in the database
     * @return Returns a credit card found in the database
     */
    @GetMapping("users/cards/number/{number}")
    public ResponseEntity<CreditCard> getByCardNumber(@PathVariable("number") String number) {
        return cardService.getByCardNumber(number);
    }

    /**
     * Finds creditCard based on userId provided
     *
     * @param userId userId of the user in the database
     * @return Returns a list of credit cards found in the database
     */
    @GetMapping("users/userId/{userId}")
    public ResponseEntity<List<CreditCard>> getByUserId(@PathVariable("userId") int userId) {
        return cardService.getByUserId(userId);
    }

}
