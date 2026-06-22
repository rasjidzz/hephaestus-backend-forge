package com.example.training_2.service;

import com.example.training_2.repository.LoanApplicationRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.training_2.dto.CreateCustomerRequest;
import com.example.training_2.dto.CustomerResponse;
import com.example.training_2.dto.LoanApplicationResponse;
import com.example.training_2.dto.PatchCustomerRequest;
import com.example.training_2.dto.UpdateCustomerRequest;
import com.example.training_2.entity.Customer;
import com.example.training_2.entity.LoanApplication;
import com.example.training_2.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;

    // CustomerService(LoanApplicationRepository loanApplicationRepository) {
    // this.loanApplicationRepository = loanApplicationRepository;
    // }

    private void fill(Customer customer, CreateCustomerRequest request) {
        customer.setFullName(request.getFullName());
        customer.setNik(request.getNik());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
    }

    public CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId()).fullName(customer.getFullName()).email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .createdAt(customer.getCreatedAt()).updatedAt(customer.getUpdatedAt()).build();
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .nik(customer.getNik())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    @Transactional
    public CustomerResponse create(CreateCustomerRequest request) {
        Customer customer = new Customer();
        fill(customer, request);
        return toResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAll(String full_name) {
        List<Customer> customers = new ArrayList<>();
        if (full_name != null) {
            customers = customerRepository.findByFullNameContainingIgnoreCase(full_name);
        } else {
            customers = customerRepository.findAll();
        }

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : customers) {
            customerResponses.add(mapToResponse(customer));
        }
        return customerResponses;
    }

    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        return mapToResponse(customer);
    }

    public CustomerResponse update(Long id, UpdateCustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setFullName(request.getFullName());
        customer.setNik(request.getNik());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());

        customerRepository.save(customer);

        return mapToResponse(customer);
    }

    public CustomerResponse patch(Long id, PatchCustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (request.getFullName() != null) {
            customer.setFullName(request.getFullName());
        }

        if (request.getNik() != null) {
            customer.setNik(request.getNik());
        }

        if (request.getEmail() != null) {
            customer.setEmail(request.getEmail());
        }

        if (request.getPhoneNumber() != null) {
            customer.setPhoneNumber(request.getPhoneNumber());
        }

        customerRepository.save(customer);

        return mapToResponse(customer);
    }

    public void delete(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }

    public List<LoanApplicationResponse> getLoanApplicationsByCustomerId(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with id: " + customerId));

        List<LoanApplication> loanApplications = loanApplicationRepository.findLoansByCustomerId(customerId);

        return loanApplications.stream()
                .map(this::mapLoanApplicationToResponse)
                .toList();
    }

    private LoanApplicationResponse mapLoanApplicationToResponse(
            LoanApplication loanApplication) {

        LoanApplicationResponse response = new LoanApplicationResponse();

        response.setId(loanApplication.getId());
        response.setCustomerId(loanApplication.getCustomer().getId());
        response.setLoanAmount(loanApplication.getLoanAmount());
        response.setTenorMonth(loanApplication.getTenorMonth());
        response.setPurpose(loanApplication.getPurpose());
        response.setStatus(loanApplication.getStatus());
        response.setCreatedAt(loanApplication.getCreatedAt());
        response.setUpdatedAt(loanApplication.getUpdatedAt());

        return response;
    }
}
