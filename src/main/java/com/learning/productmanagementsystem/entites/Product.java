package com.learning.productmanagementsystem.entites;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.constants.Country;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {
    @Id
    private int id;
    private String name;
    private double price;
    private LocalDate manufacturedDate;
    private int quantity;
    private String manufacturedBy;
    private Country origin;
    private Category category;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
