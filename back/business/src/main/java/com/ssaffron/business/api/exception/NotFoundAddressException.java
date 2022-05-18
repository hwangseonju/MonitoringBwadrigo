package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundAddressException extends NullPointerException{

    private ErrorCode errorCode;

    public NotFoundAddressException(String message){
        super(message);
    }

    // 에러 클래스에서 에러 코드 전달
    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

}
