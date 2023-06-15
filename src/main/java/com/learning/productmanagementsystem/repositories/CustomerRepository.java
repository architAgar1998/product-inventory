package com.learning.productmanagementsystem.repositories;

import com.learning.productmanagementsystem.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmailAddress(String emailAddress);
}
