package org.abpira.identity.exceptions;


import org.abpira.identity.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
                                                                             WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressMustNotBeEmptyException.class)
    public ResponseEntity<ErrorResponseDTO> handleAddressMustNotBeEmptyException(AddressMustNotBeEmptyException ex,
                                                                             WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserDoesNotExistsException(UserDoesNotExistsException ex,
                                                                             WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }
}
