package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.entites.Address;
import com.learning.productmanagementsystem.entites.Customer;
import com.learning.productmanagementsystem.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public void save(List<Address> addresses) {
        addressRepository.saveAll(addresses);
    }
}
