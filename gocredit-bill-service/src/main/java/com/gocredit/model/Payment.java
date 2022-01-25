package com.gocredit.model;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(generator = "payment_sequence", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)
    @Column(name = "paymentid")
    private Integer paymentId;

    @Column(name = "orderid")
    private String orderId;

    @Column
    private double amount;

    @Column
    private String status;

    @Column
    private String payment;

    @Column(name = "refundstatus")
    private String refundStatus;

    @Column
    private String method;

    @Column
    private String email;

    @Column
    private String contact;

    @OneToOne(mappedBy = "payments")
    private Bill bill;

}
