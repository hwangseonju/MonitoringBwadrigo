package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class DuplicatedEmailException extends DuplicateKeyException {

    private ErrorCode errorCode;

    public DuplicatedEmailException(String message){
        super(message);
        log.info("이미 존재하는 이메일");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

}
