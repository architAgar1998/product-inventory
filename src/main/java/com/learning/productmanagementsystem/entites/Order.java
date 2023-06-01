package com.learning.productmanagementsystem.entites;

import com.learning.productmanagementsystem.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Order {
    @Id
    private int id;
    private LocalDate orderDate;
    @OneToMany(mappedBy = "order")
    private List<Product> product;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address shippingAddress;
    private LocalDate deliveryDate;
    private Status status;
}
