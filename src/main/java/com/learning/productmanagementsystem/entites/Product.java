package com.learning.productmanagementsystem.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
//@Index(name = "", columnList = , unique = false)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private LocalDate manufacturedDate;
    private int quantity;
    private String manufacturedBy;
    @Enumerated(EnumType.STRING)
    private Country origin;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
