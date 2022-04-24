package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullAddressException extends NullPointerException{
    public NullAddressException(){
        super("주소가 입력되지 않아 서비스를 이용하실 수 없습니다.");
        log.info("주소가 입력되지 않아 서비스를 이용하실 수 없습니다.");
    }
}
