package com.ssaffron.business.api.success;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuccessHandler {

    public void sendSuccessLog(SuccessCode code, String uri){
        SuccessResponse successResponse = new SuccessResponse(code);
        MDC.put("Code",successResponse.getCode());
        MDC.put("MSG",successResponse.getMessage());
        log.info(uri);
    }
}
