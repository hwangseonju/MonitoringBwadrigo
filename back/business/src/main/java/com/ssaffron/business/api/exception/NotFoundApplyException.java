package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundApplyException extends NullPointerException{
    // PlanService - updateApply, deleteApply : 요금제 수정, 삭제 시 신청해놓은 서비스가 없으면 수정 및 삭제하지 않기 위한 예외 처리

    // 에러를 정의한 열거형 클래스
    private ErrorCode errorCode;

    public NotFoundApplyException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    // 에러 클래스에서 에러 메시지 전달
    public NotFoundApplyException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public NotFoundApplyException(String message){
        super(message);
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

}
