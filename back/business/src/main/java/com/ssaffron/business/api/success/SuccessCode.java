package com.ssaffron.business.api.success;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssaffron.business.api.exception.EnumModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuccessCode implements EnumModel {

    //MEMBER
    REGISTER_MEMBER(200,"K6S104-M001","Signup Success"),
    UPDATE_MEMBER(200,"K6S104-M002","Update Success"),
    DELETE_MEMBER(200,"K6S104-M003","Delete Success"),

    //ORDER
    COLLECTION_REQUEST(200,"K6S104-O001","Request Collection Success"),

    //PLAN
    INSERT_APPLY(200,"K6S104-P001","Apply Plan Success"),
    UPDATE_APPLY(200,"K6S104-P002","Update Plan Success"),
    DELETE_APPLY(200,"K6S104-P003","Delete Plan Success");


    private int status;
    private String code;
    private String message;
    private String detail;

    SuccessCode(int status, String code, String message){
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
