package com.yunusAhmet.rentACar.core.security;

import com.yunusAhmet.rentACar.business.CustomerManager;
import com.yunusAhmet.rentACar.entity.SecurityCustomer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    private final CustomerManager customerManager;

    public JwtFilter(JwtUtil jwtUtil, CustomerManager customerManager) {
        this.jwtUtil = jwtUtil;
        this.customerManager = customerManager;
    }


    @Override

    protected void doFilterInternal
            (
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain
            ) throws ServletException, IOException {

        String token = getToken(request);
        String username;

        if (!token.isBlank()) {
            username = jwtUtil.verifyJWT(token).getSubject();
            SecurityCustomer userDetails = (SecurityCustomer) customerManager.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            return "";
        }
        return header.substring(7);
    }
}
