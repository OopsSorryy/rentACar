package com.yunusahmet.rentacar.dataAccess;

import com.yunusahmet.rentacar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
    Optional<Customer> findCustomerByEmail(String email);
}
