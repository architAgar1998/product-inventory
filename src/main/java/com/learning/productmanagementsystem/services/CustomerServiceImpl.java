package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.dtos.CustomerDTO;
import com.learning.productmanagementsystem.entites.Address;
import com.learning.productmanagementsystem.entites.Customer;
import com.learning.productmanagementsystem.exceptions.CustomerAlreadyExistException;
import com.learning.productmanagementsystem.exceptions.CustomerNotFoundException;
import com.learning.productmanagementsystem.mappers.CustomerMapper;
import com.learning.productmanagementsystem.repositories.AddressRepository;
import com.learning.productmanagementsystem.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Optional<Customer> customerOpt = customerMapper.map(customerDTO, true);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer details are invalid.");
        }
        Customer customer = customerOpt.get();
        try {
            get(customer.getEmailAddress());
            throw new CustomerAlreadyExistException(String.format("Customer with Email Address: %s already exist. Please try with another email address", customer.getEmailAddress()));
        } catch (CustomerNotFoundException exception) {
            Customer savedCustomer = customerRepository.save(customer);
            List<Address> addresses = customerDTO.getAddresses();
            addressService.setCustomer(addresses, savedCustomer);
            addressService.save(addresses);
            customerDTO.setId(savedCustomer.getId());
            customerDTO.setPassword(savedCustomer.getPassword());
        }
        return customerDTO;
    }

    @Override
    public CustomerDTO get(int id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer with Id: %s not found. Please enter a valid customer id.", id));
        }
        return customerMapper.map(customerOpt.get()).orElseThrow(() -> new RuntimeException("Unable to parse customer dto to customer"));
     }

    @Override
    public CustomerDTO get(String emailAddress) {
        Optional<Customer> customerOpt = customerRepository.findByEmailAddress(emailAddress);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer with Email Address: %s not found. Please enter a valid email address.", emailAddress));
        }
        return customerMapper.map(customerOpt.get()).orElseThrow(() -> new RuntimeException("Unable to parse customer dto to customer"));
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = get(customerDTO.getId());
        customerDTO.setEmailAddress(savedCustomer.getEmailAddress());
        customerDTO.setPassword(savedCustomer.getPassword());
        customerDTO.setOrders(savedCustomer.getOrders());
        Optional<Customer> customerOpt = customerMapper.map(customerDTO, false);
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Unable to parse customer to customer dto");
        }
        Customer updatedCustomer = customerRepository.save(customerOpt.get());
        return customerMapper.map(updatedCustomer).orElseThrow(() -> new RuntimeException("Unable to parse customer to customer dto"));
    }

    @Override
    public boolean remove(int customerId) {
        CustomerDTO customerDTO = get(customerId);
        if (Objects.isNull(customerDTO)) {
            throw new CustomerNotFoundException(String.format("Customer with Id: %s not found. Please enter a valid customer id.", customerId));
        }
        customerRepository.deleteById(customerId);
        return true;
    }
}
