package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class DuplicatedEmailException extends DuplicateKeyException {

    private ErrorCode errorCode;

    public DuplicatedEmailException(String messgae, ErrorCode errorCode){
        super(messgae);
        this.errorCode = errorCode;
        log.info("이미 존재하는 이메일");
    }

    public DuplicatedEmailException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DuplicatedEmailException(String messgae){
        super(messgae);
        log.info("이미 존재하는 이메일");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

//    public DuplicatedEmailException(String memberEmail){
//        super("이미 존재하는 이메일: "+memberEmail);
//        log.info("이미 존재하는 이메일: {}", memberEmail);
//    }
}
