package com.agent.shop.services;

import com.agent.shop.config.JwtTokenUtil;
import com.agent.shop.config.JwtUserDetailsService;
import com.agent.shop.exceptions.AuthenticationException;
import com.agent.shop.model.dto.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String login(LoginRequestDTO loginRequestDTO) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new AuthenticationException("Invalid credentials");
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }
}
