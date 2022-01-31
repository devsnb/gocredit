package com.gocredit.controllers;

import com.gocredit.model.Bill;
import com.gocredit.model.Payment;
import com.gocredit.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment-api")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/payments/bills/{billId}")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment, @PathVariable("bill") int bill) {
//        Payment createdPayment = paymentService.addPayment(bill, payment);
//        return  ResponseEntity.ok(pay)
        return null;
    }
}
