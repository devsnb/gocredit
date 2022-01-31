package com.gocredit.controllers;

import com.gocredit.model.User;
import com.gocredit.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("admin-api")
public class AdminController {
    @Autowired
    private IUserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

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
     * @return Returns a list of users found in the database
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
     * @param email Email of the user in the database
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

    /**
     * Finds a single user id from the database based on the user id provided
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
}
