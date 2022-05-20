package com.ssaffron.business.api.exception;

public class TooManyException extends RuntimeException{

    private ErrorCode errorCode;

    public TooManyException(String message){
        super(message);
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
