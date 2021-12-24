package com.gocredit.service;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.User;

import java.util.List;

public interface IUserService {
    //    User
    User signup(User user) throws UserNotFoundException;

    User loginWithEmail(String email, String password) throws UserNotFoundException;

    User loginWithContactNumber(Long contactNumber, String password) throws UserNotFoundException;

    User updateUser(User user) throws UserNotFoundException;

    void deleteUser(int userId) throws UserNotFoundException;

    //    Admin methods
    User getById(int userId) throws UserNotFoundException;

    List<User> getAll();

    List<User> getByRole(String role) throws UserNotFoundException;

    List<User> getByName(String name) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;

    User getByContactNumber(long contactNumber) throws UserNotFoundException;

    User getByCardNumber(String cardNumber) throws CreditCardNotFoundException;

    User getByBillId(int billId) throws BillNotFoundException;

    List<User> getByIsBillPaid(boolean isPaid) throws UserNotFoundException;


}
