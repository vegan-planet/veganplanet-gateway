package com.veganplanet.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *  用户鉴权等信息验证、接口
 *  并将用户信息添加到 转发的请求头上
 *
 * @date 2023/12/21 21:06
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String serviceName = route.getId().replace("-route", "");
        log.info("当前请求服务：{}和请求路径{}", serviceName,path);
        //判断当前请求路径是否需要校验
        return chain.filter(exchange);
    }

    /**
     * <p>优先级</p>
     *
     * @date 2023/12/21 21:39
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
