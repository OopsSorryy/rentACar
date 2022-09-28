package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.core.exception.CustomerEmailAlreadyExistException;
import com.yunusAhmet.rentACar.core.exception.CustomerNotFoundException;
import com.yunusAhmet.rentACar.dataAccess.CustomerDao;
import com.yunusAhmet.rentACar.dto.CreateCustomerRequest;
import com.yunusAhmet.rentACar.dto.CustomerDto;
import com.yunusAhmet.rentACar.dto.UpdateCustomerRequest;
import com.yunusAhmet.rentACar.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerManager {

    private final CustomerDao customerDao;

    private final ModelMapper modelMapper;

    public CustomerManager(CustomerDao customerDao, ModelMapper modelMapper) {
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
    }

    protected Customer getCustomerByCustomerId(int customerId){
        return customerDao.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(Constant.CUSTOMER_NOT_FOUND));
    }
    protected void findTheSameCustomerEmail(CreateCustomerRequest request) {
        Optional<Customer> customer = customerDao.findCustomerByEmail(request.getEmail());
        if (customer.isPresent()) {
            throw new CustomerEmailAlreadyExistException(Constant.CUSTOMER_EMAIL_ALREADY_EXIST);
        }
    }

    public CustomerDto createCustomer(CreateCustomerRequest request){
        findTheSameCustomerEmail(request);
        Customer customer = new Customer(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword());
        return modelMapper.map(customerDao.save(customer),CustomerDto.class);
    }

    public void deleteCustomerByCustomerId(int customerId){
        customerDao.deleteById(getCustomerByCustomerId(customerId).getCustomerId());
    }

    public CustomerDto updateCustomer(UpdateCustomerRequest request){
        Customer customer = getCustomerByCustomerId(request.getCustomerId());
        customer.setPassword(request.getPassword());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        return modelMapper.map(customerDao.save(customer),CustomerDto.class);
    }
}
