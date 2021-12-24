package com.gocredit.service;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.model.Bill;
import com.gocredit.model.User;

import java.util.List;

public interface IBillService {
    User addBill(User user);

    User updateBill(String email, String password);

    User deleteBill(Long contactNumber, String password);

    User getBillById(User user);

    void getAllBills(int deleteId);

    List<Bill> getByUser(int userId) throws BillNotFoundException;

    List<Bill> getByBillerName(String userId, String billerName) throws BillNotFoundException;

    List<Bill> getByBillingDate(String date) throws BillNotFoundException;

    List<Bill> getByLessAmount(double amount) throws BillNotFoundException;

    List<Bill> getByGreaterAmount(double amount) throws BillNotFoundException;

    List<Bill> getByCardNumber(String cardNumber) throws BillNotFoundException;

    List<Bill> getByIsPaid(boolean isPaid) throws BillNotFoundException;

}
