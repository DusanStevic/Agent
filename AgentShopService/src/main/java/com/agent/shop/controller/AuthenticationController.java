package com.agent.shop.controller;

import com.agent.shop.exceptions.AuthenticationException;
import com.agent.shop.model.dto.LoginRequestDTO;
import com.agent.shop.model.dto.LoginResponseDTO;
import com.agent.shop.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO0) throws AuthenticationException {
        String token = authenticationService.login(loginRequestDTO0);
        return new ResponseEntity(new LoginResponseDTO(token), HttpStatus.OK);
    }
}
