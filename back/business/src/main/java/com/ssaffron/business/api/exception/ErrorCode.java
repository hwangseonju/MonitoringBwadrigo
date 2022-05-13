package com.ssaffron.business.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel{

    // COMMON
    INVALID_CODE(400, "C001", "Invalid Code"),
    RESOURCE_NOT_FOUND(400, "C002", "Resource Not Found"),
    DUPLICATED_RESOURCE(400, "C003", "Duplicated Resource"),
    NOT_MATCHED_PASSWORD(400, "C004", "Not Matched Password"),

    // AWS
    ASW_ERROR(400, "A001", "aws client error");

    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
