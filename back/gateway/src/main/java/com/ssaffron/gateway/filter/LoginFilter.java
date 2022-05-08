package com.ssaffron.gateway.filter;

import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Map;
import java.util.StringTokenizer;

@Component
public class LoginFilter extends AbstractGatewayFilterFactory<LoginFilter.Config> {
    private static final Logger logger = LogManager.getLogger(LoginFilter.class);
    public LoginFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            logger.info("LoginFilter baseMessage>>>>>>" + config.getBaseMessage());

            ServerWebExchange newExchange = null;
            if (config.isPreLogger()) {
                ServerHttpRequest request = exchange.getRequest();
                logger.info("LoginFilter Start>>>>>>" + exchange.getRequest());

                String bodyString = getRequestBody(request);
                DataBuffer bodyDataBuffer = stringBuffer(bodyString);

                URI requestUri = request.getURI();
                URI ex = UriComponentsBuilder.fromUri(requestUri).build(true).toUri();
                ServerHttpRequest newRequest = request.mutate().uri(ex).build();

                Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
                newRequest = new ServerHttpRequestDecorator(newRequest) {
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return bodyFlux;
                    }
                };

                newExchange = exchange.mutate().request(newRequest).build();
            }

            return chain.filter(newExchange).then(Mono.fromRunnable(()->{
                if (config.isPostLogger()) {
                    logger.info("LoginFilter End>>>>>>" + exchange.getResponse());
                }
            }));
        });
    }

    private DataBuffer stringBuffer(String value){
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new
                NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    private String getRequestBody(ServerHttpRequest request) {
        Flux<DataBuffer> body = request.getBody();
        StringBuilder sb = new StringBuilder();

        String memberEmail = "hi@hi.com";
        String memberRole = "ROLE_USER";
        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            String result = httpConnection("http://localhost:8081/v1/api/member/login", bodyString);
            System.out.println("result>>>>>" + result);
        });
//        StringTokenizer st = new StringTokenizer(str, "{,},:");
//        st.nextToken();
//        String memberEmail = st.nextToken();
//        String memberRole = st.nextToken();
//        String memberEmail = "hi@hi.com";
//        String memberRole = "ROLE_USER";
        System.out.println("제발1>>>>" + memberEmail);
        System.out.println("제발2>>>>" + memberRole);
        String str = "test";
        if (str != null) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("memberEmail", memberEmail);
                obj.put("memberRole", memberRole);
                str = obj.toString();
            }catch(Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return str;
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

        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String returnData = "";

        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true); //OutputStream을 사용해서 post body 데이터 전송
            try (OutputStream os = conn.getOutputStream()){
                byte request_data[] = ParamData.getBytes("utf-8");
                os.write(request_data);
                os.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : "+"POST");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+UrlData);
            System.out.println("http 요청 데이터 : "+ParamData);
            System.out.println("");

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            //http 요청 응답 코드 확인 실시
            String responseCode = String.valueOf(conn.getResponseCode());
            System.out.println("http 응답 코드 : "+responseCode);
            System.out.println("http 응답 데이터 : "+returnData);

        } catch (IOException e) {   // 서버 연결 끊겼을 때
            e.printStackTrace();
            logger.error("error>>>>" + e);
        } finally {
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return returnData;
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
