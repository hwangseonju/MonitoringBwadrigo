package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.exception.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class BaRestControllerAdvice {

    @ExceptionHandler(value = {NotFoundAddressException.class, NotFoundApplyException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity nullPointerHandler(NullPointerException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        response.setDetatil(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicatedApplyException.class, DuplicatedEmailException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity duplicatedKeyHandler(DuplicateKeyException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        response.setDetatil(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotMatchedPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity runtimeHandler(NotMatchedPasswordException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_MATCHED_PASSWORD);
        response.setDetatil(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
