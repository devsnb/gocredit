package com.gocredit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private CreditCard creditCard;
}