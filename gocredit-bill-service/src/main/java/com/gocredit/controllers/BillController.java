package com.gocredit.controllers;

import com.gocredit.model.Bill;
import com.gocredit.service.IBillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bill-api")
@CrossOrigin("http://localhost:4200")
public class BillController {

    @Autowired
    private IBillService billService;

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    /**
     * Adds a bill in the database
     *
     * @param bill The bill object
     * @return Returns the saved bill from the database
     */
    @PostMapping("/bills")
    ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        logger.info("POST /bill-api/bills");
        logger.debug("Inside bill controller");
        logger.debug("Inside addBill method");
        Bill createdBill = billService.addBill(bill);
        logger.debug("billService.addBill called");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBill);
    }

    /**
     * Updates a bill in the database
     *
     * @param bill The bill object
     * @return Returns the updated bill from the database
     */
    @PutMapping("/bills")
    ResponseEntity<Bill> updateBill(@RequestBody Bill bill) {
        logger.info("PUT /bill-api/bills");
        logger.debug("Inside bill controller");
        logger.debug("Inside updateBill method");
        Bill updatedBill = billService.updateBill(bill);
        logger.debug("billService.updateBill called");
        return ResponseEntity.ok(updatedBill);
    }

    /**
     * Deletes a bill from the database based on the billId
     *
     * @param billId The bill id for the bill we want to delete
     */
    @DeleteMapping("bills/{billId}")
    ResponseEntity<Void> deleteBill(@PathVariable("billId") int billId) {
        logger.info("DELETE /bill-api/bills/{billId}");
        logger.debug("Inside bill controller");
        logger.debug("Inside deleteBill method");
        billService.deleteBill(billId);
        logger.debug("billService.deleteBill called");
        return ResponseEntity.ok().build();
    }

    /**
     * Finds and returns a Bill object from the database based on the bill id
     *
     * @param billId Bill id for which we want to find the bill
     * @return Returns the bill object found in the database
     */
    @GetMapping("/bills/{billId}")
    ResponseEntity<Bill> getByBillId(@PathVariable("billId") int billId) {
        logger.info("GET /bill-api/bills/{billId}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByBillId method");
        Bill bill = billService.getByBillId(billId);
        logger.debug("billService.getByBillId called");
        return ResponseEntity.ok(bill);
    }

    /**
     * Returns all the available bills in the database
     *
     * @return Returns all the available bills in the database
     */
    @GetMapping("/bills")
    ResponseEntity<List<Bill>> getAll() {
        logger.info("GET /bill-api/bills");
        logger.debug("Inside bill controller");
        logger.debug("Inside getAll method");
        List<Bill> bills = billService.getAll();
        logger.debug("billService.getAll called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Returns all the available bills in the database for a particular user
     *
     * @param userId The id of the user we want to get all the bills for
     * @return Returns a list of bills for the user id provided
     */
    @GetMapping("/bills/user/{userId}")
    ResponseEntity<List<Bill>> getByUser(@PathVariable("userId") int userId) {
        logger.info("GET /bill-api/bills/user/{userId}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByUser method");
        List<Bill> bills = billService.getByUser(userId);
        logger.debug("billService.getByUser called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds and returns all the bill for a particular user for a particular biller name
     *
     * @param userId The id of the user we want to get all the bills for
     * @param name   Name of the biller
     * @return Returns a list of bills for the particular data provided
     */
    @GetMapping("/bills/user/{userId}/biller/{name}")
    ResponseEntity<List<Bill>> getByBillerName(@PathVariable("userId") int userId, @PathVariable("name") String name) {
        logger.info("GET /bill-api/bills/user/{userId}/biller/{name}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByBillerName method");
        List<Bill> bills = billService.getByBillerName(userId, name);
        logger.debug("billService.getByBillerName called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds and returns all the bills found for a particular user and billing date
     *
     * @param userId The id of the user we want to get all the bills for
     * @param date   The date we want to find the bills for
     * @return Returns a list of bills for the particular date provided
     */
    @GetMapping("/bills/user/{userId}/date/{date}")
    ResponseEntity<List<Bill>> getByBillingDate(@PathVariable("userId") int userId, @PathVariable("date") String date) {
        logger.info("GET bill-api/bills/user/{userId}/date/{date}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByBillingDate method");
        List<Bill> bills = billService.getByBillingDate(userId, date);
        logger.debug("billService.getByBillingDate called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds bills for a single user on lesser amount
     *
     * @param userId User id of the user for finding bills
     * @param amount Amount for lesser bills
     * @return Returns a list of bills found in the database for a given user for provided lesser amount
     */
    @GetMapping("/bills/user/{userId}/amount/less/{amount}")
    ResponseEntity<List<Bill>> getByLessAmount(@PathVariable("userId") int userId, @PathVariable("amount") double amount) {
        logger.info("GET bill-api/bills/user/{userId}/amount/less/{amount}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByLessAmount method");
        List<Bill> bills = billService.getByLessAmount(userId, amount);
        logger.debug("billService.getByLessAmount called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds bills for a user on greater amount
     *
     * @param userId User id of the user we want to fetch the bills for
     * @param amount Amount for greater bills
     * @return Returns a list of bills found in the database for a given user for provided greater amount
     */
    @GetMapping("/bills/user/{userId}/amount/greater/{amount}")
    ResponseEntity<List<Bill>> getByGreaterAmount(@PathVariable("userId") int userId, @PathVariable("amount") double amount) {
        logger.info("GET bill-api/bills/user/{userId}/amount/greater/{amount}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByGreaterAmount method");
        List<Bill> bills = billService.getByGreaterAmount(userId, amount);
        logger.debug("billService.getByGreaterAmount called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds all bills for a particular card number
     *
     * @param cardNumber The card number we want to fetch the bills for
     * @return Returns all the bills bor a card number
     */
    @GetMapping("/bills/card/{number}")
    ResponseEntity<List<Bill>> getByCardNumber(@PathVariable("number") String cardNumber) {
        logger.info("GET bill-api/bills/card/{number}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByCardNumber method");
        List<Bill> bills = billService.getByCardNumber(cardNumber);
        logger.debug("billService.getByCardNumber called");
        return ResponseEntity.ok(bills);
    }



    /**
     * Finds all bills for a particular card number
     *
     * @param cardId The card number we want to fetch the bills for
     * @return Returns all the bills bor a card number
     */
    @GetMapping("/bills/cardId/{cardId}")
    ResponseEntity<List<Bill>> getByCardId(@PathVariable("cardId") int cardId) {
        logger.info("GET bill-api/bills/card/{cardId}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByCardId method");
        List<Bill> bills = billService.getByCardId(cardId);
        logger.debug("billService.getByCardId called");
        return ResponseEntity.ok(bills);
    }


    /**
     * Finds all the bills for a user with status paid or unpaid
     *
     * @param userId The user id we want to fetch the bills for
     * @param isPaid The boolean status of the bill
     * @return Returns a list of bills found in the database based on the provided info
     */
    @GetMapping("/bills/user/{userId}/paid/{paid}")
    ResponseEntity<List<Bill>> getByIsPaid(@PathVariable("userId") int userId, @PathVariable("paid") boolean isPaid) {
        logger.info("GET bill-api/bills/user/{userId}/paid/{paid}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByIsPaid method");
        List<Bill> bills = billService.getByIsPaid(userId, isPaid);
        logger.debug("billService.getByIsPaid called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds bills for a card number with a lesser amount
     *
     * @param cardNumber The card number we want to fetch the bills for
     * @param amount     The amount for which we want bills of lesser amount
     * @return Returns a list of bills from the database based on the provided info
     */
    @GetMapping("/bills/card/{number}/bill/less/{amount}")
    ResponseEntity<List<Bill>> getByCardAndLessAmount(@PathVariable("number") String cardNumber, @PathVariable("amount") double amount) {
        logger.info("GET bill-api/bills/card/{number}/bill/less/{amount}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByCardAndLessAmount method");
        List<Bill> bills = billService.getByCardAndLessAmount(cardNumber, amount);
        logger.debug("billService.getByCardAndLessAmount called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds bills for a card number with a greater amount
     *
     * @param cardNumber The card number we want to fetch the bills for
     * @param amount     The amount for which we want bills of greater amount
     * @return Returns a list of bills from the database based on the provided info
     */
    @GetMapping("/bills/card/{number}/bill/greater/{amount}")
    ResponseEntity<List<Bill>> getByCardAndGreaterAmount(@PathVariable("number") String cardNumber, @PathVariable("amount") double amount) {
        logger.info("GET bill-api/bills/card/{number}/bill/greater/{amount}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByCardAndGreaterAmount method");
        List<Bill> bills = billService.getByCardAndGreaterAmount(cardNumber, amount);
        logger.debug("billService.getByCardAndGreaterAmount called");
        return ResponseEntity.ok(bills);
    }

    /**
     * Finds all the bills in the database for a particular card number based on bill status paid or not
     *
     * @param cardNumber The card number we want to fetch all bills for
     * @param isPaid     The boolean status for the bill
     * @return Returns a list of bill for the provided info
     */
    @GetMapping("/bills/card/{card}/paid/{paid}")
    public ResponseEntity<List<Bill>> getByCardAndIsPaid(@PathVariable("card") String cardNumber, @PathVariable("paid") boolean isPaid) {
        logger.info("GET bill-api/bills/card/{card}/paid/{paid}");
        logger.debug("Inside bill controller");
        logger.debug("Inside getByCardAndIsPaid method");
        List<Bill> bills = billService.getByCardAndIsPaid(cardNumber, isPaid);
        logger.debug("billService.getByCardAndIsPaid called");
        return ResponseEntity.ok(bills);
    }

}
