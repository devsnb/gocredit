package com.gocredit.service;

import com.gocredit.exceptions.PaymentNotFoundException;
import com.gocredit.model.Bill;
import com.gocredit.model.Payment;
import com.razorpay.RazorpayException;

import java.util.List;

public interface IPaymentService {
    Payment addPayment(Bill bill, Payment payment) throws RazorpayException;

    Payment updatePayment(Payment payment) throws PaymentNotFoundException;

    void deletePayment(int paymentId);

    Payment getByPaymentId(int paymentId) throws PaymentNotFoundException;

    List<Payment> getAll();

    List<Payment> getByUser(int userId) throws PaymentNotFoundException;
}
