package com.nicoarbio.cardealership.vehiclemodel.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ResponseHeaderFilter extends OncePerRequestFilter {

    private final static String X_SERVICE_NAME = "X-Service-Name";

    @Value("${spring.application.name}")
    private String SERVICE_NAME;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        response.setHeader(X_SERVICE_NAME, SERVICE_NAME);

        chain.doFilter(request, response);
    }

}
