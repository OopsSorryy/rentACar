package com.yunusahmet.rentacar.core.validator.create;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yunusahmet.rentacar.dto.CreateCustomerRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        CreateCustomerRequest customer = ( CreateCustomerRequest) obj;
        return customer.getPassword().equals(customer.getMatchingPassword());
    }


}