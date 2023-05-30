package com.learning.productmanagementsystem.entites;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.constants.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
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
}
