package com.example.training_2.service;

import com.example.training_2.repository.LoanApplicationRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.training_2.dto.CreateCustomerRequest;
import com.example.training_2.dto.CustomerResponse;
import com.example.training_2.dto.CustomerSummaryResponse;
import com.example.training_2.dto.LoanApplicationResponse;
import com.example.training_2.dto.PatchCustomerRequest;
import com.example.training_2.dto.UpdateCustomerRequest;
import com.example.training_2.entity.Customer;
import com.example.training_2.entity.LoanApplication;
import com.example.training_2.exception.CustomerAlreadyExistsException;
import com.example.training_2.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;

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

        log.info(
                "Creating customer. email={}, nik={}",
                request.getEmail(),
                request.getNik());

        if (customerRepository.existsByNik(request.getNik())) {

            log.warn(
                    "Customer creation failed. NIK already exists. nik={}",
                    request.getNik());

            throw new CustomerAlreadyExistsException(
                    "Customer already exists with NIK: " + request.getNik());
        }

        if (customerRepository.existsByEmail(request.getEmail())) {

            log.warn(
                    "Customer creation failed. Email already exists. email={}",
                    request.getEmail());

            throw new CustomerAlreadyExistsException(
                    "Customer already exists with email: " + request.getEmail());
        }

        Customer customer = new Customer();
        fill(customer, request);
        customer = customerRepository.save(customer);

        log.info(
                "Customer created successfully. id={}, email={}",
                customer.getId(),
                customer.getEmail());

        return toResponse(customer);
    }

    public List<CustomerResponse> getAll(String full_name) {
        log.debug("Searching customers. fullName={}", full_name);

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

        log.debug("Found {} customers", customers.size());

        return customerResponses;
    }

    public CustomerResponse getById(Long id) {
        // log.debug("Finding customer. id={}", id);
        // Customer customer = customerRepository.findById(id)
        // .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found. id={}", id);
                    return new RuntimeException("Customer not found");
                });

        return mapToResponse(customer);
    }

    public CustomerResponse update(Long id, UpdateCustomerRequest request) {
        log.info("Updating customer. id={}", id);

        // Customer customer = customerRepository.findById(id)
        // .orElseThrow(() -> new RuntimeException("Customer not found"));
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found. id={}", id);
                    return new RuntimeException("Customer not found");
                });

        customer.setFullName(request.getFullName());
        customer.setNik(request.getNik());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());

        customerRepository.save(customer);

        log.info("Customer updated successfully. id={}", id);

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

        log.info("Deleting customer. id={}", id);

        // Customer customer = customerRepository.findById(id)
        // .orElseThrow(() -> new RuntimeException("Customer not found"));

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found. id={}", id);
                    return new RuntimeException("Customer not found");
                });

        customerRepository.delete(customer);

        log.info("Customer deleted successfully. id={}", id);
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

        Customer customer = loanApplication.getCustomer();

        LoanApplicationResponse response = new LoanApplicationResponse();

        CustomerSummaryResponse customerResponse = CustomerSummaryResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .nik(customer.getNik())
                .email(customer.getEmail())
                .build();

        response.setId(loanApplication.getId());
        response.setCustomer(customerResponse);
        response.setLoanAmount(loanApplication.getLoanAmount());
        response.setTenorMonth(loanApplication.getTenorMonth());
        response.setPurpose(loanApplication.getPurpose());
        response.setStatus(loanApplication.getStatus());
        response.setCreatedAt(loanApplication.getCreatedAt());
        response.setUpdatedAt(loanApplication.getUpdatedAt());

        return response;
    }
}
