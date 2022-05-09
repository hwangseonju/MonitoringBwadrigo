package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class DuplicatedApplyException extends DuplicateKeyException {

    private ErrorCode errorCode;

    public DuplicatedApplyException(String messgae, ErrorCode errorCode){
        super(messgae);
        this.errorCode = errorCode;
        log.info("님께서 이미 신청한 서비스가 있습니다.");
    }

    public DuplicatedApplyException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DuplicatedApplyException(String message){
        super(message);
        log.info("님께서 이미 신청한 서비스가 있습니다.");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

//    public DuplicatedApplyException(String memberName){
//        super(memberName + "님께서 이미 신청한 서비스가 있습니다.");
//        log.info("{}님께서 이미 신청한 서비스가 있습니다.", memberName);
//    }
}
