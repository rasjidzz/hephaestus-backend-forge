package com.example.training.service;

import com.example.training.exception.CustomerNotFoundException;
import com.example.training.exception.GlobalExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.training.dto.CreateCustomerRequest;
import com.example.training.dto.CustomerResponse;
import com.example.training.dto.PatchCustomerRequest;
import com.example.training.dto.UpdateCustomerRequest;
import com.example.training.model.Customer;

@Service
public class CustomerService {
    private final GlobalExceptionHandler globalExceptionHandler;
    private Map<Long, Customer> customerStorage = new HashMap<>();
    private Long sequence = 1L;

    CustomerService(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    // helper toCustomerResponse
    public CustomerResponse toCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setCreatedAt(customer.getCreatedAt());
        customerResponse.setUpdatedAt(customer.getUpdatedAt());
        return customerResponse;
    }

    public List<CustomerResponse> getCustomers(String email, String full_name) {
        List<CustomerResponse> customerResponses = new ArrayList<>();

        for (Customer customer : customerStorage.values()) {
            CustomerResponse customerResponse = new CustomerResponse();
            if (email != null && !email.isEmpty()) {
                if (customer.getEmail().toLowerCase().contains(email.toLowerCase())) {
                    customerResponse = toCustomerResponse(customer);
                    customerResponses.add(customerResponse);
                }
            } else if (full_name != null && !full_name.isEmpty()) {
                if (customer.getFullName().toLowerCase().contains(full_name.toLowerCase())) {
                    customerResponse = toCustomerResponse(customer);
                    customerResponses.add(customerResponse);
                }
            } else {
                customerResponse = toCustomerResponse(customer);
                customerResponses.add(customerResponse);
            }
        }
        return customerResponses;
    }

    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest entity) {
        Customer newCustomer = new Customer(sequence, entity.getFullName(), entity.getEmail(), entity.getPhoneNumber());
        customerStorage.put(sequence, newCustomer);

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId(sequence);
        customerResponse.setFullName(newCustomer.getFullName());
        customerResponse.setEmail(newCustomer.getEmail());
        customerResponse.setPhoneNumber(newCustomer.getPhoneNumber());
        customerResponse.setCreatedAt(newCustomer.getCreatedAt());
        customerResponse.setUpdatedAt(newCustomer.getUpdatedAt());

        sequence++;
        return customerResponse;
    }

    public CustomerResponse getCustomerById(@PathVariable Long id) {
        Customer customer = customerStorage.get(id);

        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setCreatedAt(customer.getCreatedAt());
        customerResponse.setUpdatedAt(customer.getUpdatedAt());

        return customerResponse;
    }

    public CustomerResponse getDefaultCustomer() {
        return buildCustomerResponse(1L, "Edith Dorothy", "mrisjads@gmail.com", "082112570672");
    }

    private CustomerResponse buildCustomerResponse(Long id, String fullName, String email, String phoneNumber) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(id);
        customerResponse.setFullName(fullName);
        customerResponse.setEmail(email);
        customerResponse.setPhoneNumber(phoneNumber);
        customerResponse.setCreatedAt(ZonedDateTime.now());
        customerResponse.setUpdatedAt(ZonedDateTime.now());

        return customerResponse;
    }

    public CustomerResponse deleteCustomerById(@PathVariable Long id) {
        Customer customer = customerStorage.get(id);

        if (customer == null) {
            throw new RuntimeException("Customer tidak ditemukan");
        }

        customerStorage.remove(id);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(id);
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());

        return customerResponse;
    }

    public CustomerResponse updateCustomerById(@PathVariable Long id, @RequestBody UpdateCustomerRequest entity) {
        Customer customer = customerStorage.get(id);

        if (customer == null) {
            throw new RuntimeException("Customer dengan ID " + id + " tidak ditemukan");
        }

        if (entity.getFullName() != null && !entity.getFullName().isEmpty()) {
            customer.setFullName(entity.getFullName());
        }
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            customer.setEmail(entity.getEmail());
        }
        if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isEmpty()) {
            customer.setPhoneNumber(entity.getPhoneNumber());
        }

        customer.setUpdatedAt(ZonedDateTime.now());
        customerStorage.put(id, customer);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());

        return customerResponse;
    }

    public CustomerResponse patchCustomerById(@PathVariable Long id, @RequestBody PatchCustomerRequest entity) {
        Customer customer = customerStorage.get(id);

        if (customer == null) {
            throw new RuntimeException("Customer dengan ID " + id + " tidak ditemukan");
        }

        // Update hanya field yang tidak null
        if (entity.getFullName() != null && !entity.getFullName().isEmpty()) {
            customer.setFullName(entity.getFullName());
        }
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            customer.setEmail(entity.getEmail());
        }
        if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isEmpty()) {
            customer.setPhoneNumber(entity.getPhoneNumber());
        }

        customer.setUpdatedAt(ZonedDateTime.now());
        customerStorage.put(id, customer);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());

        return customerResponse;
    }
}