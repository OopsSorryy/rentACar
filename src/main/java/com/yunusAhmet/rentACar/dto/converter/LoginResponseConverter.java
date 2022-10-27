package com.yunusAhmet.rentACar.dto.converter;

import com.yunusAhmet.rentACar.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginResponseConverter {


    public LoginResponse convert(String token){
        return new LoginResponse(token);

    }
}
