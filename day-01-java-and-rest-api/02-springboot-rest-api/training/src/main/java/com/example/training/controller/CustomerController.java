package com.example.training.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.dto.WebResponse;
import com.example.training.dto.CreateCustomerRequest;
import com.example.training.dto.CustomerResponse;
import com.example.training.dto.PatchCustomerRequest;
import com.example.training.dto.UpdateCustomerRequest;
import com.example.training.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v2/customers")
@Tag(name = "Customer Controller", description = "API for Customer Transaction")
public class CustomerController {

        private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
                this.customerService = customerService;
        }

        @PostMapping
        @Operation
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Customer Created Successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data")
        })
        public ResponseEntity<WebResponse<CustomerResponse>> createCustomer(
                        @Valid @RequestBody CreateCustomerRequest request) {
                CustomerResponse response = customerService.createCustomer(request);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(WebResponse.success("Customer berhasil ditambahkan", response));
        }

        @GetMapping
        @Operation
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Customer Fetched Successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data")
        })
        public ResponseEntity<WebResponse<List<CustomerResponse>>> getAllCustomer(
                        @RequestParam(required = false) String full_name,
                        @RequestParam(required = false) String email) {
                List<CustomerResponse> responses;

                responses = customerService.getCustomers(email, full_name);
                return ResponseEntity.ok()
                                .body(WebResponse.success("Berhasil mendapatkan semua customer", responses));
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get customer by ID", description = "Endpoint ini digunakan untuk mengambil data spesifik customer berdasarkan ID yang diberikan.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Customer Fetched Successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
        public ResponseEntity<WebResponse<CustomerResponse>> getCustomerById(@PathVariable long id) {
                CustomerResponse response = customerService.getCustomerById(id);
                return ResponseEntity.ok()
                                .body(WebResponse.success("Berhasil mendapatkan customer dengan ID " + id, response));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete customer by ID", description = "Endpoint ini digunakan untuk menghapus data spesifik customer berdasarkan ID yang diberikan.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Customer Deleted Successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
        public ResponseEntity<WebResponse<CustomerResponse>> deleteCustomerById(@PathVariable long id) {
                CustomerResponse response = customerService.deleteCustomerById(id);
                return ResponseEntity.ok()
                                .body(WebResponse.success("Customer dengan ID " + id + " berhasil dihapus", response));
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update customer by ID (Full Update)", description = "Endpoint ini digunakan untuk memperbarui seluruh data customer berdasarkan ID. Semua field pada request body harus diisi sesuai dengan aturan validasi.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Customer Updated Successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data (Validation Error)"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
        public ResponseEntity<WebResponse<CustomerResponse>> updateCustomerById(@PathVariable long id,
                        @Valid @RequestBody UpdateCustomerRequest request) {
                CustomerResponse response = customerService.updateCustomerById(id, request);
                return ResponseEntity.ok()
                                .body(WebResponse.success("Customer dengan ID " + id + " berhasil diupdate", response));
        }

        @PatchMapping("/{id}")
        @Operation(summary = "Patch customer by ID (Partial Update)", description = "Endpoint ini digunakan untuk memperbarui sebagian data customer berdasarkan ID. Hanya field yang dikirim pada request body yang akan diperbarui.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500")
        })
        public ResponseEntity<WebResponse<CustomerResponse>> patchCustomerById(@PathVariable long id,
                        @RequestBody @Valid PatchCustomerRequest request) {
                CustomerResponse response = customerService.patchCustomerById(id, request);
                return ResponseEntity.ok()
                                .body(WebResponse.success("Customer dengan ID " + id + " berhasil di patch", response));
        }
}