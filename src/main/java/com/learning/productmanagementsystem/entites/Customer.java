package com.learning.productmanagementsystem.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    private String emailAddress;
    private String password;
    private long phoneNo;
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
