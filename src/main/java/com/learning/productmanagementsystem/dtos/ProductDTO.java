package com.learning.productmanagementsystem.dtos;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.constants.Country;
import com.learning.productmanagementsystem.entites.Order;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDTO implements DTO {
    private int id;
    private String name;
    private double price;
    private LocalDate manufacturedDate;
    private int quantity;
    private String manufacturedBy;
    private Country origin;
    private Category category;
    private Order order;
}
