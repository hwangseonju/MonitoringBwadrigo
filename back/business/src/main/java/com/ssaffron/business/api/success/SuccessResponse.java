package com.ssaffron.business.api.success;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SuccessResponse {

    private String message;
    private String code;
    private int status;
    private String detail;

    public SuccessResponse(SuccessCode code){
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.detail = code.getDetail();
    }

    public static SuccessResponse of(SuccessCode code){
        return new SuccessResponse(code);
    }
}
