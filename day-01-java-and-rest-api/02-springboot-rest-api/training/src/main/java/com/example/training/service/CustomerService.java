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

    // Old Code
    // public List<CustomerResponse> getCustomers() {
    // List<CustomerResponse> responses = new ArrayList<>();

    // for (Customer customer : customerStorage.values()) {
    // CustomerResponse customerResponse = new CustomerResponse();
    // customerResponse.setId(customer.getId());
    // customerResponse.setFullName(customer.getFullName());
    // customerResponse.setEmail(customer.getEmail());
    // customerResponse.setPhoneNumber(customer.getPhoneNumber());
    // customerResponse.setCreatedAt(customer.getCreatedAt());
    // customerResponse.setUpdatedAt(customer.getUpdatedAt());

    // responses.add(customerResponse);
    // }
    // return responses;
    // }
    // public List<CustomerResponse> searchCustomerByName(String full_name) {
    // List<CustomerResponse> responses = new ArrayList<>();

    // for (Customer customer : customerStorage.values()) {
    // if (customer.getFullName().toLowerCase().contains(full_name.toLowerCase())) {
    // CustomerResponse customerResponse = new CustomerResponse();
    // customerResponse.setId(customer.getId());
    // customerResponse.setFullName(customer.getFullName());
    // customerResponse.setEmail(customer.getEmail());
    // customerResponse.setPhoneNumber(customer.getPhoneNumber());

    // responses.add(customerResponse);
    // }
    // }

    // return responses;
    // }

    // public List<CustomerResponse> searchCustomerByEmail(String email) {
    // List<CustomerResponse> responses = new ArrayList<>();

    // for (Customer customer : customerStorage.values()) {
    // if (customer.getEmail().toLowerCase().contains(email.toLowerCase())) {
    // CustomerResponse customerResponse = new CustomerResponse();
    // customerResponse.setId(customer.getId());
    // customerResponse.setFullName(customer.getFullName());
    // customerResponse.setEmail(customer.getEmail());
    // customerResponse.setPhoneNumber(customer.getPhoneNumber());

    // responses.add(customerResponse);
    // }
    // }

    // return responses;
    // }
    // Old Code

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
        List<CustomerResponse> responses = new ArrayList<>();

        for (Customer customer : customerStorage.values()) {
            CustomerResponse customerResponse = new CustomerResponse();
            if (email != null && !email.isEmpty()) {
                if (customer.getEmail().toLowerCase().contains(email.toLowerCase())) {
                    customerResponse = toCustomerResponse(customer);
                    responses.add(customerResponse);
                }
            } else if (full_name != null && !full_name.isEmpty()) {
                if (customer.getFullName().toLowerCase().contains(full_name.toLowerCase())) {
                    customerResponse = toCustomerResponse(customer);
                    responses.add(customerResponse);
                }
            } else {
                customerResponse = toCustomerResponse(customer);
                responses.add(customerResponse);
            }
        }
        return responses;
    }

    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest entity) {
        Customer newCust = new Customer(sequence, entity.getFullName(), entity.getEmail(), entity.getPhoneNumber());
        customerStorage.put(sequence, newCust);

        CustomerResponse response = new CustomerResponse();

        response.setId(sequence);
        response.setFullName(newCust.getFullName());
        response.setEmail(newCust.getEmail());
        response.setPhoneNumber(newCust.getPhoneNumber());
        response.setCreatedAt(newCust.getCreatedAt());
        response.setUpdatedAt(newCust.getUpdatedAt());

        sequence++;
        return response;
    }

    public CustomerResponse getCustomerById(@PathVariable Long id) {
        Customer cust = customerStorage.get(id);

        if (cust == null) {
            throw new CustomerNotFoundException(id);
        }

        CustomerResponse response = new CustomerResponse();
        response.setId(cust.getId());
        response.setEmail(cust.getEmail());
        response.setFullName(cust.getFullName());
        response.setPhoneNumber(cust.getPhoneNumber());
        response.setCreatedAt(cust.getCreatedAt());
        response.setUpdatedAt(cust.getUpdatedAt());

        return response;
    }

    public CustomerResponse getDefaultCustomer() {
        return buildCustomerResponse(1L, "Edith Dorothy", "mrisjads@gmail.com", "082112570672");
    }

    private CustomerResponse buildCustomerResponse(Long id, String fullName, String email, String phoneNumber) {
        CustomerResponse response = new CustomerResponse();
        response.setId(id);
        response.setFullName(fullName);
        response.setEmail(email);
        response.setPhoneNumber(phoneNumber);
        response.setCreatedAt(ZonedDateTime.now());
        response.setUpdatedAt(ZonedDateTime.now());

        return response;
    }

    public CustomerResponse deleteCustomerById(@PathVariable Long id) {
        Customer cust = customerStorage.get(id);

        if (cust == null) {
            throw new RuntimeException("Customer tidak ditemukan");
        }

        customerStorage.remove(id);
        CustomerResponse response = new CustomerResponse();
        response.setId(id);
        response.setEmail(cust.getEmail());
        response.setFullName(cust.getFullName());
        response.setPhoneNumber(cust.getPhoneNumber());

        return response;
    }

    public CustomerResponse updateCustomerById(@PathVariable Long id, @RequestBody UpdateCustomerRequest entity) {
        Customer cust = customerStorage.get(id);

        if (cust == null) {
            throw new RuntimeException("Customer dengan ID " + id + " tidak ditemukan");
        }

        if (entity.getFullName() != null && !entity.getFullName().isEmpty()) {
            cust.setFullName(entity.getFullName());
        }
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            cust.setEmail(entity.getEmail());
        }
        if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isEmpty()) {
            cust.setPhoneNumber(entity.getPhoneNumber());
        }

        cust.setUpdatedAt(ZonedDateTime.now());
        customerStorage.put(id, cust);

        CustomerResponse response = new CustomerResponse();
        response.setId(cust.getId());
        response.setFullName(cust.getFullName());
        response.setEmail(cust.getEmail());
        response.setPhoneNumber(cust.getPhoneNumber());

        return response;
    }

    public CustomerResponse patchCustomerById(@PathVariable Long id, @RequestBody PatchCustomerRequest entity) {
        Customer cust = customerStorage.get(id);

        if (cust == null) {
            throw new RuntimeException("Customer dengan ID " + id + " tidak ditemukan");
        }

        // Update hanya field yang tidak null
        if (entity.getFullName() != null && !entity.getFullName().isEmpty()) {
            cust.setFullName(entity.getFullName());
        }
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            cust.setEmail(entity.getEmail());
        }
        if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isEmpty()) {
            cust.setPhoneNumber(entity.getPhoneNumber());
        }

        cust.setUpdatedAt(ZonedDateTime.now());
        customerStorage.put(id, cust);

        CustomerResponse response = new CustomerResponse();
        response.setId(cust.getId());
        response.setFullName(cust.getFullName());
        response.setEmail(cust.getEmail());
        response.setPhoneNumber(cust.getPhoneNumber());

        return response;
    }
}