package com.learning.productmanagementsystem.dtos;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.constants.Country;
import com.learning.productmanagementsystem.entites.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO /*implements DTO*/ {
    private int id;
    @NotNull(message = "Product name can not be null.")
    @NotBlank(message = "Product name can not be blank.")
    @NotEmpty(message = "Product name can not be empty.")
    private String name;
    private double price;
    private LocalDate manufacturedDate;
    private int quantity;
    private String manufacturedBy;
    private Country origin;
    private Category category;
//    private Order order;
}
