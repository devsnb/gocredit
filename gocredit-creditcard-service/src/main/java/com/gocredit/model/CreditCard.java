package com.gocredit.model;

import com.gocredit.model.attributeencryptors.StringAttributeEncryptor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "creditcards")
public class CreditCard {

    @Id
    @Column(name = "cardid")
    @GeneratedValue(generator = "card_sequence", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence", allocationSize = 1)
    private Integer cardId;

    @Convert(converter = StringAttributeEncryptor.class)
    @Column(name = "nameoncard", nullable = false)
    private String nameOnCard;

    @Convert(converter = StringAttributeEncryptor.class)
    @Column(name = "cardnumber", nullable = false)
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

    public CreditCard() {
    }

    public CreditCard(String nameOnCard, String cardNumber, CardType cardType, LocalDate validityDate, Set<Bill> bills, User user) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.validityDate = validityDate;
        this.bills = bills;
        this.user = user;
    }

    public CreditCard(Integer cardId, String nameOnCard, String cardNumber, CardType cardType, LocalDate validityDate, Set<Bill> bills, User user) {
        this.cardId = cardId;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.validityDate = validityDate;
        this.bills = bills;
        this.user = user;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardId=" + cardId +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType=" + cardType +
                ", validityDate=" + validityDate +
                ", user=" + user +
                '}';
    }
}