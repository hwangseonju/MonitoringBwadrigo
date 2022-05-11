package com.ssaffron.business.api.service;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    public static String getAccessToken(HttpServletRequest request) {
        String tokenValue = request.getHeader(HEADER_AUTHORIZATION);

        if (tokenValue == null) {
            return null;
        }

        if (tokenValue.startsWith(TOKEN_PREFIX)) {
            return tokenValue.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

}
