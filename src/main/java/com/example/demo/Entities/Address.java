package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Address Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;
    private String street;
    private String city;
    private String postalCode;
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Employee employee;

    public Address(String street, String city, String postalCode) {
        this.street=street;
        this.city=city;
        this.postalCode=postalCode;
    }

}