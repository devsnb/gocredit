package com.gocredit.service;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.model.Bill;

import java.util.List;

public interface IBillService {
    Bill addBill(Bill bill);

    Bill updateBill(Bill bill);

    void deleteBill(int billId);

    Bill getByBillId(int billId) throws BillNotFoundException;

    List<Bill> getAll();

    List<Bill> getByUser(int userId) throws BillNotFoundException;

    List<Bill> getByBillerName(int userId, String name) throws BillNotFoundException;

    List<Bill> getByBillingDate(int userId, String date) throws BillNotFoundException;

    List<Bill> getByLessAmount(int userId, double amount) throws BillNotFoundException;

    List<Bill> getByGreaterAmount(int userId, double amount) throws BillNotFoundException;

    List<Bill> getByCardNumber(String cardNumber) throws BillNotFoundException;

    List<Bill> getByIsPaid(int userId, boolean isPaid) throws BillNotFoundException;

    List<Bill> getByCardAndLessAmount(String cardNumber, double amount) throws BillNotFoundException;

    List<Bill> getByCardAndGreaterAmount(String cardNumber, double amount) throws BillNotFoundException;

    List<Bill> getByCardAndIsPaid(String cardNumber, boolean isPaid) throws BillNotFoundException;

    List<Bill> getByCardId(int cardId) throws  BillNotFoundException;
}
