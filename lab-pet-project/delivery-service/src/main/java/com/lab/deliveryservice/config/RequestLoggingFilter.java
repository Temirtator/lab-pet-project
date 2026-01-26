package com.lab.deliveryservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Incoming request {} {} from {} Authorization={}",
                request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), mask(auth));
        filterChain.doFilter(request, response);
    }

    private String mask(String value) {
        if (value == null) return "<none>";
        if (value.length() <= 10) return value;
        return value.substring(0, 7) + "...";
    }
}
