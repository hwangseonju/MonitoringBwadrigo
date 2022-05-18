package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class BaRestControllerAdvice {

    @ExceptionHandler(DeleteApplyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity errorHandler(DeleteApplyException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_CODE);
        response.setDetail(e.getMessage());
        MDC.put("Code",response.getCode());
        MDC.put("MSG",response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundAddressException.class, NotFoundApplyException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity nullPointerHandler(NullPointerException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        response.setDetail(e.getMessage());
        MDC.put("Code",response.getCode());
        MDC.put("MSG",response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicatedApplyException.class, DuplicatedEmailException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity duplicatedKeyHandler(DuplicateKeyException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.DUPLICATED_RESOURCE);
        response.setDetail(e.getMessage());
        MDC.put("Code",response.getCode());
        MDC.put("MSG",response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotMatchedPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity runtimeHandler(NotMatchedPasswordException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_MATCHED_PASSWORD);
        response.setDetail(e.getMessage());
        MDC.put("Code",response.getCode());
        MDC.put("MSG",response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TooManyException.class)
    @ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity tooManyHandler(TooManyException e){
        ErrorResponse response = ErrorResponse.of(ErrorCode.TOO_MANY_REQEUSTS);
        response.setDetail(e.getMessage());
        MDC.put("Code", response.getCode());
        MDC.put("MSG", response.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
    }
}
