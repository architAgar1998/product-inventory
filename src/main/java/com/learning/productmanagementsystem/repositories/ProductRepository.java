package com.learning.productmanagementsystem.repositories;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.entites.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query("DELETE FROM Product p WHERE p.category = :category")
    @Transactional
    int deleteByCategory(@Param("category") Category category);

}
