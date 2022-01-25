package com.gocredit.service;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public class RazorpayInstance {
    public static RazorpayClient init() {
        RazorpayClient razorpayClient = null;

        try {
            razorpayClient = new RazorpayClient("rzp_test_BPwyLly5fwzGHz", "KNaiTzIUFEj4BmetUGfoNErp");
        } catch (RazorpayException e) {
            e.printStackTrace();
        }

        return razorpayClient;
    }
}
