package com.agent.product.config;

import com.agent.product.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleError(Exception exception) {
        return new ResponseEntity<>(new ErrorResponseDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

}
