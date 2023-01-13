package com.yunusahmet.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunusahmet.rentacar.entity.Customer;


import java.util.Optional;


public interface CustomerDao extends JpaRepository<Customer,Integer> {
    Optional<Customer> findCustomerByEmail(String email);
}
