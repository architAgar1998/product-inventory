package com.learning.productmanagementsystem.dtos;

import com.learning.productmanagementsystem.constants.Status;
import com.learning.productmanagementsystem.entites.Address;
import com.learning.productmanagementsystem.entites.Customer;
import com.learning.productmanagementsystem.entites.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO /*implements DTO*/ {
    private int id;
    private LocalDate orderDate;
    private List<Product> product;
    private double totalPrice;
    private Customer customer;
    private Address shippingAddress;
    private LocalDate deliveryDate;
    private Status status;
}
