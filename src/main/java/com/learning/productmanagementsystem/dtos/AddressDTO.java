package com.learning.productmanagementsystem.dtos;

import com.learning.productmanagementsystem.constants.AddressType;
import com.learning.productmanagementsystem.constants.Country;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class AddressDTO /*implements DTO*/ {
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int pinCode;
    private String state;
    private Country country;
    private AddressType type;
}
