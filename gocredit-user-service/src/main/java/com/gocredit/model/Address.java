package com.gocredit.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(generator = "address_sequence", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @Column(name = "addressid")
    private Integer addressId;

    @Column(name = "doorno", length = 10)
    private String doorNo;

    @Column(name = "streetname", length = 30)
    private String streetName;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 20, nullable = false)
    private String state;

    @Column(nullable = false)
    private int zipcode;

    public Address(String doorNo, String streetName, String city, String state, int zipcode) {
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
}
