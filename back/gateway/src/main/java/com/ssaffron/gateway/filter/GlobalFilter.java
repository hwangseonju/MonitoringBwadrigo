package com.ssaffron.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global filter baseMessage:  {}", config.getBaseMessage());

            if(config.isPreLogger())
                log.info("Global filter Start - URI:  {}", request.getURI());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostLogger())
                    log.info("Global filter End:  {}", response);
            }));

        };
    }




    @Data
    //application.yml 파일에서 설정해 줄 것임
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }


}