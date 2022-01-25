package com.gocredit.service;

import com.gocredit.exceptions.PaymentNotFoundException;
import com.gocredit.model.Bill;
import com.gocredit.model.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Override
    public Payment addPayment(Bill bill, Payment payment) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", bill.getAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + String.valueOf(Math.round(Math.random() * 99999999)));
        Order order = RazorpayInstance.init().Orders.create(options);
        String id = order.get("id");
        double amount = order.get("amount");
        String status = order.get("status");

        System.out.println("Id : " + id);
        System.out.println("Amount : " + amount);
        System.out.println("Status : " + status);
        System.out.println(order);

        payment.setStatus(status);
        payment.setAmount(amount);
        payment.setOrderId(id);
        return null;
    }

    @Override
    public Payment updatePayment(Payment payment) throws PaymentNotFoundException {
        return null;
    }

    @Override
    public void deletePayment(int paymentId) {

    }

    @Override
    public Payment getByPaymentId(int paymentId) throws PaymentNotFoundException {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public List<Payment> getByUser(int userId) throws PaymentNotFoundException {
        return null;
    }
}
