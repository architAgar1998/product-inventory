package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.entites.Address;
import com.learning.productmanagementsystem.entites.Customer;

import java.util.List;

public interface AddressService {
    void save(List<Address> addresses);

    default void setCustomer(List<Address> addresses, Customer customer) {
        addresses
                .forEach(address -> address.setCustomer(customer));
    }
}
