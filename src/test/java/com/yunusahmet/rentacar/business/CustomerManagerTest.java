package com.yunusahmet.rentacar.business;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.yunusahmet.rentacar.core.exception.CustomerEmailAlreadyExistException;
import com.yunusahmet.rentacar.core.exception.CustomerNotFoundException;
import com.yunusahmet.rentacar.dataAccess.CustomerDao;
import com.yunusahmet.rentacar.dto.*;
import com.yunusahmet.rentacar.dto.converter.CustomerDtoConverter;
import com.yunusahmet.rentacar.entity.Customer;
import com.yunusahmet.rentacar.entity.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CustomerManagerTest {

    private CustomerDao customerDao;

    private CustomerDtoConverter customerDtoConverter;

    private BCryptPasswordEncoder passwordEncoder;
    public CustomerManager customerManager;


    @BeforeEach
    public void setUp() {
        customerDao = mock(CustomerDao.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        customerManager = new CustomerManager(customerDao, passwordEncoder, customerDtoConverter);
    }

    @Test
    public void testCreateCustomer_whenCustomerEmailIsNotSame_shouldReturnCustomerDto() {
        CreateCustomerRequest request = new CreateCustomerRequest
                (
                        "ahmet",
                        "dayı",
                        "ahmetdayı26@gmail.com",
                        "Ahmet.26",
                        "Ahmet.26"
                );

        String encodedPasword = passwordEncoder.encode(request.getPassword());
        String encodedPasword2 = passwordEncoder.encode(request.getMatchingPassword());
        Customer customer = new Customer
                (
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        encodedPasword,
                        encodedPasword2,
                        Role.USER
                );
        Customer saveCustomer = new Customer
                (
                        1,
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        encodedPasword, encodedPasword2,
                        Role.USER);
        CustomerDto expected = new CustomerDto
                (
                        1,
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail()
                );


        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPasword);
        when(passwordEncoder.encode(request.getMatchingPassword())).thenReturn(encodedPasword2);
        when(customerDao.save(customer)).thenReturn(saveCustomer);
        when(customerDtoConverter.convert(saveCustomer)).thenReturn(expected);


        CustomerDto result = customerManager.createCustomer(request);

        assertEquals(expected, result);
//        verify(passwordEncoder).encode(request.getPassword());
//        verify(passwordEncoder).encode(request.getMatchingPassword());
        verify(customerDao).save(customer);
        verify(customerDtoConverter).convert(saveCustomer);

    }

    @Test
    public void testCreateCustomer_whenCustomerEmailAlreadyExists_shouldReturnException() {
        CreateCustomerRequest request = new CreateCustomerRequest
                (
                        "ahmet",
                        "dayı",
                        "ahmetdayı26@gmail.com",
                        "Ahmet.26",
                        "Ahmet.26"
                );
        Customer customer = new Customer
                (
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getMatchingPassword()
                );
        when(customerDao.findCustomerByEmail(customer.getEmail())).
                thenReturn(Optional.of(customer));

        assertThrows(CustomerEmailAlreadyExistException.class, () -> customerManager.createCustomer(request));
        verify(customerDao).findCustomerByEmail(request.getEmail());

    }

    @Test
    public void testUpdateCustomer_whenCustomerIdExists_shouldReturnCustomerDto() {
        UpdateCustomerRequest request = new UpdateCustomerRequest
                (
                        1,
                        "ahmet",
                        "dayı",
                        "Ahmet.26",
                        "Ahmet.26");
        Customer customer = new Customer
                (
                        1,
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        request.getMatchingPassword());
        CustomerDto expected = new CustomerDto
                (
                        1,
                        request.getFirstName(),
                        request.getLastName(),
                        "ahmetdayı42@gmail.com"
                );


        when(customerDao.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
        when(customerDao.save(customer)).thenReturn(customer);
        when(customerDtoConverter.convert(customer)).thenReturn(expected);
        CustomerDto result = customerManager.updateCustomer(request);

        assertEquals(expected, result);
        verify(customerDao).findById(customer.getCustomerId());
        verify(customerDao).save(customer);
        verify(customerDtoConverter).convert(customer);
    }

    @Test
    public void testDeleteAndUpdateCustomer_whenCustomerIdDoesntExist_shouldReturnException() {

        when(customerDao.findById(1)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerManager.getCustomerByCustomerId(1));
    }

    @Test
    public void testDeleteCustomer_whenCustomerIdExists_shouldDeleteCustomer() {
        int customerId = 1;

        Customer customer = new Customer(customerId, "Ahmet", "Dayi", "Ahmet.26", "Ahmet.26");

        when(customerDao.findById(1)).thenReturn(Optional.of(customer));

        customerManager.deleteCustomerByCustomerId(customer.getCustomerId());

        verify(customerDao).findById(customerId);
        verify(customerDao).deleteById(customer.getCustomerId());
    }


}