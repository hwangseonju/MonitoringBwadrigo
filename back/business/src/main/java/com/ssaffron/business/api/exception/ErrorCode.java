package com.ssaffron.business.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel{

    // WARNING
    INVALID_CODE(400, "K6S104-W001", "Invalid Code"),
    RESOURCE_NOT_FOUND(400, "K6S104-W002", "Resource Not Found"),
    DUPLICATED_RESOURCE(400, "K6S104-W003", "Duplicated Resource"),
    NOT_MATCHED_PASSWORD(400, "K6S104-W004", "Not Matched Password"),
    UNAUTHORIZATION(401, "K6S104-W005", "Invalid JWT"),
    JWT_EXPIRED(401, "K6S104-W006", "EXPIRED JWT"),

    // ERROR
    TOO_MANY_REQEUSTS(429, "K6S104-E001", "Too Many Requests"),

    // AWS
    ASW_ERROR(400, "K6S104-A001", "aws client error");

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
