package com.learning.productmanagementsystem.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.productmanagementsystem.constants.AddressType;
import com.learning.productmanagementsystem.constants.Country;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Field;
import java.util.List;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int pinCode;
    private String state;
    @Enumerated(value = EnumType.STRING)
    private Country country;
    @Enumerated(value = EnumType.STRING)
    private AddressType type;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "shippingAddress")
    private List<Order> orders;
}
