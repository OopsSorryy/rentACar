package com.yunusahmet.rentacar.business;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.yunusahmet.rentacar.core.security.JwtUtil;
import com.yunusahmet.rentacar.dto.LoginRequest;
import com.yunusahmet.rentacar.dto.LoginResponse;
import com.yunusahmet.rentacar.dto.converter.LoginResponseConverter;

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
