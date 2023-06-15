package com.learning.productmanagementsystem.repositories;

import com.learning.productmanagementsystem.entites.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
