package com.learning.productmanagementsystem.controllers;

import com.learning.productmanagementsystem.dtos.CustomerDTO;
import com.learning.productmanagementsystem.dtos.Response;
import com.learning.productmanagementsystem.exceptions.CustomerAlreadyExistException;
import com.learning.productmanagementsystem.exceptions.CustomerNotFoundException;
import com.learning.productmanagementsystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<CustomerDTO> create(@RequestBody CustomerDTO customer) {
        Response<CustomerDTO> customerDTOResponse = new Response<>();
        try {
            CustomerDTO customerDTO = customerService.create(customer);
            customerDTOResponse.setSuccessResponse(HttpStatus.CREATED.value(), "Customer has been registered", customerDTO);
        } catch (IllegalArgumentException exception) {
            customerDTOResponse.setResponse(HttpStatus.NO_CONTENT.value(), exception.getMessage());
        } catch (CustomerAlreadyExistException exception) {
            customerDTOResponse.setResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
        } catch (Exception exception) {
            customerDTOResponse.setResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to register the customer.");
        }
        return customerDTOResponse;
    }

    @GetMapping("id/{id}")
    public Response<CustomerDTO> get(@PathVariable int id) {
        Response<CustomerDTO> customerDTOResponse = new Response<>();
        try {
            CustomerDTO customerDTO = customerService.get(id);
            customerDTOResponse.setSuccessResponse(customerDTO);
        } catch (CustomerNotFoundException exception) {
            customerDTOResponse.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            customerDTOResponse.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to fetch the customer");
        }
        return customerDTOResponse;
    }
    @GetMapping("email/{emailAddress}")
    public Response<CustomerDTO> get(@PathVariable String emailAddress) {
        Response<CustomerDTO> customerDTOResponse = new Response<>();
        try {
            CustomerDTO customerDTO = customerService.get(emailAddress);
            customerDTOResponse.setSuccessResponse(customerDTO);
        } catch (CustomerNotFoundException exception) {
            customerDTOResponse.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            customerDTOResponse.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to fetch the customer");
        }
        return customerDTOResponse;
    }
    @PutMapping
    public Response<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        Response<CustomerDTO> customerDTOResponse = new Response<>();
        try {
            CustomerDTO updateCustomerDTO = customerService.update(customerDTO);
            customerDTOResponse.setSuccessResponse(updateCustomerDTO);
        } catch (CustomerNotFoundException exception) {
            customerDTOResponse.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            customerDTOResponse.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to fetch the customer");
        }
        return customerDTOResponse;
    }
    @DeleteMapping("/{id}")
    public Response<Boolean> remove(@PathVariable int customerId) {
        Response<Boolean> response = new Response<>();
        try {
            boolean deleted = customerService.remove(customerId);
            response.setSuccessResponse(deleted);
        } catch (CustomerNotFoundException exception) {
            response.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            response.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to fetch the customer");
        }
        return response;
    }
}
