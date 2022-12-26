package com.yunusahmet.rentacar.core.validator.update;

import com.yunusahmet.rentacar.dto.UpdateCustomerRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdatePasswordMatchesValidator implements ConstraintValidator<UpdatePasswordMatches, Object> {

    @Override
    public void initialize(UpdatePasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UpdateCustomerRequest customer = ( UpdateCustomerRequest) obj;
        return customer.getPassword().equals(customer.getMatchingPassword());
    }


}
