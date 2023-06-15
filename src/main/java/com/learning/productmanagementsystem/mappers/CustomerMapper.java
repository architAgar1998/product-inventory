package com.learning.productmanagementsystem.mappers;

import com.learning.productmanagementsystem.dtos.CustomerDTO;
import com.learning.productmanagementsystem.entites.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<Customer> map(CustomerDTO customerDTO, boolean encode) {
        if (Objects.isNull(customerDTO)) {
            return Optional.empty();
        }
        String password = encode ? passwordEncoder.encode(customerDTO.getPassword()): customerDTO.getPassword();
        return Optional.of(
                Customer.builder()
                .name(customerDTO.getName())
                .addresses(customerDTO.getAddresses())
                .emailAddress(customerDTO.getEmailAddress())
                .orders(customerDTO.getOrders())
                .phoneNo(customerDTO.getPhoneNo())
                .password(password)
                .build()
        );
    }

    public Optional<CustomerDTO> map(Customer customer) {
        if (Objects.isNull(customer)) {
            return Optional.empty();
        }
        return Optional.of(
                CustomerDTO.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .addresses(customer.getAddresses())
                        .emailAddress(customer.getEmailAddress())
                        .orders(customer.getOrders())
                        .phoneNo(customer.getPhoneNo())
                        .build()
        );
    }
}