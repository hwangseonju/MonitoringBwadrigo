package com.ssaffron.gateway.filter;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);
    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            logger.info("AuthFilter baseMessage>>>>>>" + config.getBaseMessage());
            if (config.isPreLogger()) {
                ServerHttpRequest request = exchange.getRequest();
                logger.info("AuthFilter Start>>>>>>" + exchange.getRequest());

                // token이 있는지 없는 여부 확인
                if(!request.getHeaders().containsKey("Cookie")) {
                    return handleUnAuthorized(exchange);
                }

                // token 가져오기
                List<String> token = exchange.getRequest().getHeaders().get("Cookie");
                String tokenString = Objects.requireNonNull(token).get(0);
                logger.info("token 확인>>>>>>"+tokenString);

                // 토큰 확인
                //String url = "https://webhook.site/1840c760-d74f-4696-8fac-bfb7a185925c";
                String url = "http://localhost:8082/v1/api/auth/test";

                if(!httpConnection(url,tokenString).equals("200")) {
                    return handleUnAuthorized(exchange);
                }
            }

            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if (config.isPostLogger()) {
                    logger.info("AuthFilter End>>>>>>" + exchange.getResponse());
                }
            }));
        });
    }

    private Mono<Void> handleUnAuthorized(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static String httpConnection(String UrlData, String ParamData) {

        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        totalUrl = UrlData.trim().toString();

        //http 통신을 하기위한 객체 선언 실시
        URL url = null;
        HttpURLConnection conn = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String result = "";

        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Cookie", ParamData);

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : "+"GET");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+UrlData);
            System.out.println("http 요청 데이터 : "+ParamData);
            System.out.println("");

            //http 요청 후 응답
            result = conn.getResponseMessage();

            //http 요청 응답 코드 확인 실시
            String responseCode = String.valueOf(conn.getResponseCode());
            result = responseCode;
            System.out.println("http 응답 코드 : "+responseCode);
            System.out.println("http 응답 데이터 : "+result);

        } catch (IOException e) {   // 서버 연결 끊겼을 때
            e.printStackTrace();
            logger.error("error>>>>" + e);
        }

        return result;
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
