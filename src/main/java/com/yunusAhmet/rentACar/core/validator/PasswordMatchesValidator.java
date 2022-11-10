package com.yunusAhmet.rentACar.core.validator;


import com.yunusAhmet.rentACar.dto.CreateCustomerRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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