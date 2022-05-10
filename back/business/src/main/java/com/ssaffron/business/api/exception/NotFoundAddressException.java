package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundAddressException extends NullPointerException{

    private ErrorCode errorCode;

    public NotFoundAddressException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
        log.info("주소가 입력되지 않아 서비스를 이용하실 수 없습니다.");
    }

    // 에러 클래스에서 에러 메시지 전달
    public NotFoundAddressException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public NotFoundAddressException(String message){
        super(message);
        log.info("주소가 입력되지 않아 서비스를 이용하실 수 없습니다.");
    }

    // 에러 클래스에서 에러 코드 전달
    public ErrorCode getErrorCode(){
        return this.errorCode;
    }


//    public NotFoundAddressException(){
//        super("주소가 입력되지 않아 서비스를 이용하실 수 없습니다.");
//        log.info("주소가 입력되지 않아 서비스를 이용하실 수 없습니다.");
//    }
}
