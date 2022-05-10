package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistedApplyException extends RuntimeException{

    private ErrorCode errorCode;

    public ExistedApplyException(String messgae, ErrorCode errorCode){
        super(messgae);
        this.errorCode = errorCode;
        log.info("님께서 기존에 신청한 서비스와 동일합니다.");
    }

    public ExistedApplyException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ExistedApplyException(String messgae){
        super(messgae);
        log.info("님께서 기존에 신청한 서비스와 동일합니다.");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

//    public ExistedApplyException(String memberName){
//        super(memberName + "님께서 기존에 신청한 서비스와 동일합니다.");
//        log.info("{}님께서 기존에 신청한 서비스와 동일합니다.", memberName);
//    }
}
