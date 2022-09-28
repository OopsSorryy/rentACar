package com.yunusAhmet.rentACar.dataAccess;

import com.yunusAhmet.rentACar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
    Optional<Customer> findCustomerByEmail(String email);
}
