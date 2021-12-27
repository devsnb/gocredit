package com.gocredit.model;

import com.gocredit.model.attributeencryptors.AttributeEncryptor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "creditcards")
public class CreditCard {

    @Id
    @Column(name = "cardid")
    @GeneratedValue(generator = "card_sequence", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence", allocationSize = 1)
    private Integer cardId;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "nameofcard", length = 30, nullable = false)
    private String nameOnCard;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "cardnumber", length = 30, nullable = false,unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "cardtype", nullable = false)
    private CardType cardType;

    @Column(name = "validitydate", nullable = false)
    private LocalDate validityDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "billid")
    private Set<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}