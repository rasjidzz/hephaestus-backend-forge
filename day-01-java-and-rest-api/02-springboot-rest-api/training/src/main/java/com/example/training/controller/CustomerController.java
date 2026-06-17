package com.example.training.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.dto.ApiResponse;
import com.example.training.dto.CreateCustomerRequest;
import com.example.training.dto.CustomerResponse;
import com.example.training.dto.UpdateCustomerRequest;
import com.example.training.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // @PostMapping
    //
    // ic ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody
    // CreateCustomerRequest request) {
    // CustomerResponse response = customerService.createCustomer(request);
    // return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // }

    // @GetMapping
    //
    // ic ResponseEntity<List<CustomerResponse>>
    // getAllCustomer(@RequestParam(required = false) String full_name) {
    // full_name != null && !full_name.isEmpty()) {
    // return ResponseEntity.ok(customerService.searchCustomerByName(full_name));
    // }
    // return ResponseEntity.ok(customerService.getCustomers());
    // }

    // @GetMapping("/{id}")
    //
    // ic ResponseEntity<CustomerResponse> getCustomerById(@PathVariable long id) {
    // return ResponseEntity.ok(customerService.getCustomerById(id));
    // }

    // @DeleteMapping("/{id}")
    //
    // ic ResponseEntity<CustomerResponse> deleteCustomerById(@PathVariable long id)
    // {
    // CustomerResponse response = customerService.deleteCustomerById(id);

    // return ResponseEntity.ok(response);
    // }

    // @PutMapping("/{id}")
    //
    // esponseEntity<CustomerResponse> updateCustomerById(@PathVariable long id,
    // @Valid @RequestBody UpdateCustomerRequest request) {
    // CustomerResponse response = customerService.updateCustomerById(id, request);
    // return ResponseEntity.ok(response);
    // }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer berhasil ditambahkan", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer(
            @RequestParam(required = false) String full_name) {
        List<CustomerResponse> responses;

        if (full_name != null && !full_name.isEmpty()) {
            responses = customerService.searchCustomerByName(full_name);
            return ResponseEntity.ok()
                    .body(ApiResponse.success("Pencarian customer dengan nama '" + full_name + "' berhasil",
                            responses));
        }

        responses = customerService.getCustomers();
        return ResponseEntity.ok()
                .body(ApiResponse.success("Berhasil mendapatkan semua customer", responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable long id) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok()
                .body(ApiResponse.success("Berhasil mendapatkan customer dengan ID " + id, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> deleteCustomerById(@PathVariable long id) {
        CustomerResponse response = customerService.deleteCustomerById(id);
        return ResponseEntity.ok()
                .body(ApiResponse.success("Customer dengan ID " + id + " berhasil dihapus", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomerById(@PathVariable long id,
            @Valid @RequestBody UpdateCustomerRequest request) {
        CustomerResponse response = customerService.updateCustomerById(id, request);
        return ResponseEntity.ok()
                .body(ApiResponse.success("Customer dengan ID " + id + " berhasil diupdate", response));
    }
}
