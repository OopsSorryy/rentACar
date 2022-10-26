package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.core.exception.CustomerEmailAlreadyExistException;
import com.yunusAhmet.rentACar.core.exception.CustomerNotFoundException;
import com.yunusAhmet.rentACar.dataAccess.CustomerDao;
import com.yunusAhmet.rentACar.dto.CreateCustomerRequest;
import com.yunusAhmet.rentACar.dto.CustomerDto;
import com.yunusAhmet.rentACar.dto.UpdateCustomerRequest;
import com.yunusAhmet.rentACar.entity.Customer;
import com.yunusAhmet.rentACar.entity.Role;
import com.yunusAhmet.rentACar.entity.SecurityCustomer;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerManager implements UserDetailsService {

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
    protected Customer findCustomerByEmail(String email){
        return customerDao.findCustomerByEmail(email).orElseThrow(
                ()-> new CustomerNotFoundException(Constant.CUSTOMER_NOT_FOUND));
    }

    public CustomerDto createCustomer(CreateCustomerRequest request){
        findTheSameCustomerEmail(request);
        Customer customer =
                new Customer(request.getFirstName(),
                        request.getLastName(),request.getEmail(),request.getPassword(),request.getMatchingPassword());
        return modelMapper.map(customerDao.save(customer),CustomerDto.class);
    }

    public void deleteCustomerByCustomerId(int customerId){
        customerDao.deleteById(getCustomerByCustomerId(customerId).getCustomerId());
    }

    public CustomerDto updateCustomer(UpdateCustomerRequest request){
        Customer customer = getCustomerByCustomerId(request.getCustomerId());
        customer.setPassword(request.getPassword());
        customer.setMatchingPassword(request.getMatchingPassword());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        return modelMapper.map(customerDao.save(customer),CustomerDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findCustomerByEmail(username);

        return new SecurityCustomer(customer);
//        return customerDao.findCustomerByEmail(username).map(SecurityCustomer::new).
//                orElseThrow(()-> new CustomerNotFoundException(Constant.CUSTOMER_NOT_FOUND));
    }
}
