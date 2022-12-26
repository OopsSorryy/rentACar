package com.yunusahmet.rentacar.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yunusahmet.rentacar.business.CustomerManager;
import com.yunusahmet.rentacar.dto.*;

import javax.validation.Valid;

@RequestMapping("/customer")
@RestController

public class CustomerController {

    private final CustomerManager customerManager;

    public CustomerController(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest request){
        return new ResponseEntity<>(customerManager.createCustomer(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerByCustomerId(@PathVariable int customerId){
        customerManager.deleteCustomerByCustomerId(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody UpdateCustomerRequest request){
        return new ResponseEntity<>(customerManager.updateCustomer(request),HttpStatus.CREATED);
    }

}
