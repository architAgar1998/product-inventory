package com.learning.productmanagementsystem.repositories;

import com.learning.productmanagementsystem.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
