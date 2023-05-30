package com.learning.productmanagementsystem.entites;

import com.learning.productmanagementsystem.constants.AddressType;
import com.learning.productmanagementsystem.constants.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.lang.reflect.Field;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Id
    private int customerId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int pinCode;
    private String state;
    private Country country;
    private AddressType type;
}
