package com.learning.productmanagementsystem.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.productmanagementsystem.entites.Address;
import com.learning.productmanagementsystem.entites.Order;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
public class CustomerDTO /*implements  DTO*/ {
    private int id;
    private String name;
    private String emailAddress;
    private String password;
    private long phoneNo;
    private List<Address> addresses;
    private List<Order> orders;
}