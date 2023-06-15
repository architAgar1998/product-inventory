package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.dtos.CustomerDTO;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO get(int id) ;
    CustomerDTO get(String emailAddress);
    public CustomerDTO update(CustomerDTO customerDTO);
    public boolean remove(int customerId);
}
