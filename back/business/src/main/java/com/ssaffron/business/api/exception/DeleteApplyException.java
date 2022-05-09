package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteApplyException extends RuntimeException {

    private ErrorCode errorCode;

    public DeleteApplyException(String messgae, ErrorCode errorCode){
        super(messgae);
        this.errorCode = errorCode;
        log.info("님께서 신청한 서비스가 없습니다.");
    }

    public DeleteApplyException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DeleteApplyException(String messgae){
        super(messgae);
        log.info("님께서 신청한 서비스가 없습니다.");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
//    public DeleteApplyException(String memberName){
//        super(memberName + "님께서 신청한 요금제가 없습니다.");
//        log.info("{}님께서 신청한 서비스가 없습니다.", memberName);
//    }
}
