package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotMatchedPasswordException extends RuntimeException{
    private ErrorCode errorCode;

    public NotMatchedPasswordException(String message){
        super(message);
        log.warn("기존 패스워드 불일치");
    }
}
