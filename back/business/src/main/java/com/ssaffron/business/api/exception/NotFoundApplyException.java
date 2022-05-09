package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundApplyException extends NullPointerException{

    // 에러를 정의한 열거형 클래스
    private ErrorCode errorCode;

    public NotFoundApplyException(String messgae, ErrorCode errorCode){
        super(messgae);
        this.errorCode = errorCode;
        log.info("신청한 서비스가 없습니다.");
    }

    public NotFoundApplyException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public NotFoundApplyException(String message){
        super(message);
        log.info("신청한 서비스가 없습니다.");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
//   public NotFoundApplyException(String memberName){
//        super(memberName+"님께서 신청한 서비스가 없습니다.");
//        log.info("{}님께서 신청한 서비스가 없습니다.",memberName);
//    }
}
