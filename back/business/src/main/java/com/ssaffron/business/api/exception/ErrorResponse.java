package com.ssaffron.business.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    // 동일한 포맷으로 Error를 return하기 위한 class 생성

    private String message;
    private String code;
    private int status;
    private String detatil;

    public ErrorResponse(ErrorCode code){
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.detatil = code.getDetail();
    }

    public static ErrorResponse of(ErrorCode code){
        return new ErrorResponse(code);
    }
}
