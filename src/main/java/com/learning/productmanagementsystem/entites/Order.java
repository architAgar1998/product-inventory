package com.learning.productmanagementsystem.entites;

import com.learning.productmanagementsystem.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Order {
    @Id
    private int id;
    private LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private List<Product> product;
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "shipping_address_customer_id")
    private Address shippingAddress;
    private LocalDate deliveryDate;
    private Status status;
}
