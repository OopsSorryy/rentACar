package com.yunusahmet.rentacar.dto.converter;

import org.springframework.stereotype.Component;

import com.yunusahmet.rentacar.dto.LoginResponse;

@Component
public class LoginResponseConverter {


    public LoginResponse convert(String token){
        return new LoginResponse(token);

    }
}
