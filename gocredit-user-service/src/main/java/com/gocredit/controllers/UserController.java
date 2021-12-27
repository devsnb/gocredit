package com.gocredit.controllers;

import com.gocredit.model.User;
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
@RequestMapping("user-api")
@Profile("dev")
public class UserController {

    @Autowired
    private IUserService userService;

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
     * Returns all users in the database
     *
     * @return Returns all users in the database
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        logger.info("GET /user-api/users");
        logger.debug("Inside user Controller");
        logger.debug("Inside getAll Method");
        List<User> users = userService.getAll();
        logger.debug("userService.getAll Called");
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users based on role of the user provided
     *
     * @param role Role of the user
     * @return Returns a list of user found in the database
     */
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<User>> getByRole(@PathVariable("role") String role) {
        logger.info("GET /user-api/users/role/{role}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByRole Method ");
        List<User> users = userService.getByRole(role);
        logger.debug("userService.getByName Called");
        return ResponseEntity.ok().body(users);
    }

    /**
     * Finds users based on name provided
     *
     * @param name Name of the user in the database
     * @return Returns a list of users  found in the database
     */
    @GetMapping("/users/name/{name}")
    public ResponseEntity<List<User>> getByName(@PathVariable("name") String name) {
        logger.info("GET /user-api/users/name/{name}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByName Method ");
        List<User> users = userService.getByName(name);
        logger.debug("userService.getByName Called");
        return ResponseEntity.ok().body(users);
    }

    /**
     * Finds users based on email provided
     *
     * @param email Email  of the user in the database
     * @return Returns a list of user found in the database
     */
    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable("email") String email) {
        logger.info("GET /user-api/users/email/{email}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByEmail Method ");
        User user = userService.getByEmail(email);
        logger.debug("userService.getByEmail Called");
        return ResponseEntity.ok().body(user);
    }

    /**
     * Finds User based on contact number provided
     *
     * @param contactNumber Contact number of the user in the database
     * @return Returns a list of user found in the database
     */
    @GetMapping("/users/contactNumber/{contactNumber}")
    public ResponseEntity<User> getByContactNumber(@PathVariable("contactNumber") long contactNumber) {
        logger.info("GET /user-api/users/contactNumber/{number}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByContactNumber Method ");
        User user = userService.getByContactNumber(contactNumber);
        logger.debug("userService.getByContactNumber Called");
        return ResponseEntity.ok().body(user);
    }

    /**
     * Finds User based on credit card number provided
     *
     * @param cardNumber Credit Card Number of the user in the database
     * @return Returns the user found in the database
     */
    @GetMapping("/users/cardNumber/{cardNumber}")
    public ResponseEntity<User> getByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        logger.info("GET /user-api/users/cardNumber/{cardNumber}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByCardNumber Method");
        User user = userService.getByCardNumber(cardNumber);
        logger.debug("userService.getByCardNumber Called");
        return ResponseEntity.ok().body(user);
    }

    /**
     * Finds User based on bill id provided
     *
     * @param billId Finds User based on bill id provided
     * @return Returns the bill found in the database
     */
    @GetMapping("/users/billId/{billId}")
    public ResponseEntity<User> getByBillId(@PathVariable("billId") int billId) {
        logger.info("GET /user-api/users/billId/{billId}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByBillId Method");
        User user = userService.getByBillId(billId);
        logger.debug("userService.getByBillId Called");
        return ResponseEntity.ok().body(user);
    }

    /**
     * Finds User based on Is bill paid provided
     *
     * @param isPaid Is user bill paid in the database
     * @return Returns a list of users found in the database
     */
    @GetMapping("/users/paid/{paid}")
    public ResponseEntity<List<User>> getByIsBillPaid(@PathVariable("paid") boolean isPaid) {
        logger.info("GET /user-api/users/isBillPaid/{isBillPaid}");
        logger.debug("Inside User Controller");
        logger.debug("Inside getByIsBillPaid Method");
        List<User> user = userService.getByIsBillPaid(isPaid);
        logger.debug("userService.getByIsBillPaid Called");
        return ResponseEntity.ok().body(user);
    }
}
