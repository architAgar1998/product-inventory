package com.learning.productmanagementsystem.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    private int id;
    private String name;
    private String emailAddress;
    private String password;
    private long phoneNo;
    private List<Address> addresses;
    private List<Order> orders;
}
