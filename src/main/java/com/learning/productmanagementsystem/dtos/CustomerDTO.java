package com.learning.productmanagementsystem.dtos;

import com.learning.productmanagementsystem.entites.Address;
import com.learning.productmanagementsystem.entites.Order;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO implements  DTO {
    private int id;
    private String name;
    private String emailAddress;
    private long phoneNo;
    private List<Address> addresses;
    private List<Order> orders;
}
