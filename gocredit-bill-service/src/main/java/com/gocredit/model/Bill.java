package com.gocredit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @Column(name = "billid")
    @GeneratedValue(generator = "bill_sequence", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "bill_sequence", sequenceName = "bill_sequence", allocationSize = 1)
    private Integer billId;

    @Column(name = "billername", length = 30, nullable = false)
    private String billerName;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double amount;

    @Column(name = "ispaid", nullable = false)
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "cardid")
    @JsonIgnore
    private CreditCard creditCard;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Payment payments;

    public Bill() {
    }

    public Bill(String billerName, LocalDate date, double amount, boolean isPaid, CreditCard creditCard, Payment payments) {
        this.billerName = billerName;
        this.date = date;
        this.amount = amount;
        this.isPaid = isPaid;
        this.creditCard = creditCard;
        this.payments = payments;
    }

    public Bill(Integer billId, String billerName, LocalDate date, double amount, boolean isPaid, CreditCard creditCard, Payment payments) {
        this.billId = billId;
        this.billerName = billerName;
        this.date = date;
        this.amount = amount;
        this.isPaid = isPaid;
        this.creditCard = creditCard;
        this.payments = payments;
    }


    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", billerName='" + billerName + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", isPaid=" + isPaid +
                ", creditCard=" + creditCard +
                ", payments=" + payments +
                '}';
    }
}