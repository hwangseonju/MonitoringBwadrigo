package com.ssaffron.auth.util;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String HEADER_REFERER_URI = "Referer";
    private final static String TOKEN_PREFIX = "Bearer ";
    private final static String REFERER_PREFIX = "http://localhost:3000";

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

    public static String getHeaderRedirectUri(HttpServletRequest request){
        String redirectValue = request.getHeader(HEADER_REFERER_URI);
        redirectValue = redirectValue.substring(REFERER_PREFIX.length());

        if(redirectValue == null){
            return null;
        }
        else{
            return redirectValue;
        }
    }
}
