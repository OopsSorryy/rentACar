package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.security.JwtUtil;
import com.yunusAhmet.rentACar.dto.LoginRequest;
import com.yunusAhmet.rentACar.dto.LoginResponse;
import com.yunusAhmet.rentACar.dto.converter.LoginResponseConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthManager {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final LoginResponseConverter converter;

    public AuthManager(AuthenticationManager authenticationManager, JwtUtil jwtUtil, LoginResponseConverter converter) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.converter = converter;
    }

    public LoginResponse login(LoginRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
                (request.getEmail(),
                        request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        return converter.convert(jwtUtil.generateToken(authenticate));
    }
}
