package com.gocredit.service;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.model.Bill;
import com.gocredit.model.CreditCard;
import com.gocredit.repository.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    ICreditCardService creditCardService;

    /**
     * Adds a bill in the database
     *
     * @param bill The bill object
     * @return Returns the saved bill from the database
     */
    @Override
    public Bill addBill(Bill bill, int cardId) {

        CreditCard card = creditCardService.getById(cardId).getBody();

        bill.setCreditCard(card);

        return billRepository.save(bill);
    }

    /**
     * Updates a bill in the database
     *
     * @param bill The bill object
     * @return Returns the updated bill from the database
     */
    @Override
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    /**
     * Deletes a bill from the database based on the billId
     *
     * @param billId The bill id for the bill we want to delete
     */
    @Override
    public void deleteBill(int billId) {
        billRepository.deleteById(billId);
    }

    /**
     * Finds and returns a Bill object from the database based on the bill id
     *
     * @param billId Bill id for which we want to find the bill
     * @return Returns the bill object found in the database
     * @throws BillNotFoundException If no bill is found in the database with the
     *                               provided bill id
     */
    @Override
    public Bill getByBillId(int billId) throws BillNotFoundException {
        return billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException("No bill found with the id of " + billId));
    }

    /**
     * Returns all the available bills in the database
     *
     * @return Returns all the available bills in the database
     */
    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    /**
     * Returns all the available bills in the database for a particular user
     *
     * @param userId The id of the user we want to get all the bills for
     * @return Returns a list of bills for the user id provided
     * @throws BillNotFoundException If no bill is found for the user id provided
     */
    @Override
    public List<Bill> getByUser(int userId) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByUser(userId);

        if (bills.isEmpty()) {
            throw new BillNotFoundException("No bill found for the user with the id of " + userId);
        }
        return bills;
    }

    /**
     * Finds and returns all the bill for a particular user for a particular biller
     * name
     *
     * @param userId The id of the user we want to get all the bills for
     * @param name   Name of the biller
     * @return Returns a list of bills for the particular data provided
     * @throws BillNotFoundException If no bill is found based on the data provided
     */
    @Override
    public List<Bill> getByBillerName(int userId, String name) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByBillerName(userId, name);

        if (bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bill found with the biller name " + name + " and for the user with the id of " + userId);
        }
        return bills;
    }

    /**
     * Finds and returns all the bills found for a particular user and billing date
     *
     * @param userId The id of the user we want to get all the bills for
     * @param date   The date we want to find the bills for
     * @return Returns a list of bills for the particular date provided
     * @throws BillNotFoundException If no bill is found for the information
     *                               provided
     */
    @Override
    public List<Bill> getByBillingDate(int userId, String date) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        LocalDate parsedDate = LocalDate.parse(date);

        bills = billRepository.findByBillingDate(userId, parsedDate);

        if (bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bill found with on the date of " + date + " and for the user with the id of " + userId);
        }
        return bills;
    }

    /**
     * Finds bills for a single user on lesser amount
     *
     * @param userId User id of the user for finding bills
     * @param amount Amount for lesser bills
     * @return Returns a list of bills found in the database for a given user for
     * provided lesser amount
     * @throws BillNotFoundException If no bill is found for the provided info
     */
    @Override
    public List<Bill> getByLessAmount(int userId, double amount) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByLessAmount(userId, amount);

        if (bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bill found with less amount than " + amount + " and for the user with the id of " + userId);
        }
        return bills;
    }

    /**
     * Finds bills for a user on greater amount
     *
     * @param userId User id of the user we want to fetch the bills for
     * @param amount Amount for greater bills
     * @return Returns a list of bills found in the database for a given user for
     * provided greater amount
     * @throws BillNotFoundException If no bill is found for the provided info
     */
    @Override
    public List<Bill> getByGreaterAmount(int userId, double amount) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByGreaterAmount(userId, amount);

        if (bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bill found with greater amount than " + amount + " and for the user with the id of " + userId);
        }
        return bills;
    }

    /**
     * Finds all bills for a particular card number
     *
     * @param cardNumber The card number we want to fetch the bills for
     * @return Returns all the bills bor a card number
     * @throws BillNotFoundException If no bill is found for the given credit card
     */
    @Override
    public List<Bill> getByCardNumber(String cardNumber) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByCardNumber(cardNumber);

        if (bills.isEmpty()) {
            throw new BillNotFoundException("No bill found for the credit card " + cardNumber);
        }
        return bills;
    }

    /**
     * Finds all the bills for a user with status paid or unpaid
     *
     * @param userId The user id we want to fetch the bills for
     * @param isPaid The boolean status of the bill
     * @return Returns a list of bills found in the database based on the provided
     * info
     * @throws BillNotFoundException If no bill is found in the database
     */
    @Override
    public List<Bill> getByIsPaid(int userId, boolean isPaid) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByIsPaid(userId, isPaid);

        if (bills.isEmpty()) {
            if (isPaid) {
                throw new BillNotFoundException("No bill found for the user id " + userId + " and the status of PAID");
            }
            throw new BillNotFoundException("No bill found for the user id " + userId + " and the status of NOT_PAID");
        }
        return bills;
    }

    /**
     * Finds bills for a card number with a lesser amount
     *
     * @param cardNumber The card number we want to fetch the bills for
     * @param amount     The amount for which we want bills of lesser amount
     * @return Returns a list of bills from the database based on the provided info
     * @throws BillNotFoundException If no bill is found in the database
     */
    @Override
    public List<Bill> getByCardAndLessAmount(String cardNumber, double amount) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByCardAndLessAmount(cardNumber, amount);

        if (bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bill found for the card " + cardNumber + " with any bill less than " + amount);
        }

        return bills;
    }

    /**
     * Finds bills for a card number with a greater amount
     *
     * @param cardNumber The card number we want to fetch the bills for
     * @param amount     The amount for which we want bills of greater amount
     * @return Returns a list of bills from the database based on the provided info
     * @throws BillNotFoundException If no bill is found in the database
     */
    @Override
    public List<Bill> getByCardAndGreaterAmount(String cardNumber, double amount) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByCardAndGreaterAmount(cardNumber, amount);

        if (bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bill found for the card " + cardNumber + " with any bill greater than " + amount);
        }

        return bills;
    }

    /**
     * Finds all the bills in the database for a particular card number based on
     * bill status paid or not
     *
     * @param cardNumber The card number we want to fetch all bills for
     * @param isPaid     The boolean status for the bill
     * @return Returns a list of bill for the provided info
     * @throws BillNotFoundException if no bill is found in the database based on
     *                               the provided info
     */
    @Override
    public List<Bill> getByCardAndIsPaid(String cardNumber, boolean isPaid) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findByCardAndIsPaid(cardNumber, isPaid);

        if (bills.isEmpty()) {
            if (isPaid) {
                throw new BillNotFoundException(
                        "No bill found for the card number " + cardNumber + " and the status of PAID");
            }
            throw new BillNotFoundException(
                    "No bill found for the card number " + cardNumber + " and the status of NOT_PAID");
        }

        return bills;
    }

    @Override
    public List<Bill> getBillsByCardId(int cardid) throws BillNotFoundException {
        List<Bill> bills = new ArrayList<>();

        bills = billRepository.findBillByCardId(cardid);

        if (bills.isEmpty()) {
            throw new BillNotFoundException("No bills found with the id of " + cardid);
        }

        return bills;
    }
}
