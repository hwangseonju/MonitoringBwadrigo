package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotMatchedPasswordException extends RuntimeException{
    private ErrorCode errorCode;

    public NotMatchedPasswordException(String message){
        super(message);
    }
}
