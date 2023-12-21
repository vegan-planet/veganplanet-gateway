package com.veganplanet.gateway.filter;

import cn.hutool.json.JSONUtil;
import com.veganplanet.common.core.constant.enums.ExceptionStatusEnum;
import com.veganplanet.common.core.response.Res;
import com.veganplanet.gateway.domain.AuthUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 *  用户鉴权等信息验证、接口
 *  并将用户信息添加到 转发的请求头上
 *
 * @date 2023/12/21 21:06
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String serviceName = route.getId().replace("-route", "");
        log.info("当前请求服务：{}和请求路径{}", serviceName,path);
        //判断当前请求路径是否需要登录校验
        if(antPathMatcher.match("/**/auth/**", path)) {
            //登录校验
            AuthUserInfo userInfo = this.getUserInfo(request);
            if(userInfo == null) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response, ExceptionStatusEnum.LOGIN_AUTH);
            }
            //将用户信息添加到请求头
            request.mutate().header("user-data", JSONUtil.toJsonStr(userInfo));
        }
        return chain.filter(exchange);
    }

    /**
     * <p>获取用户信息</p>
     * @date 2023/12/21 23:02
     * @param
     * @return
     */
    private AuthUserInfo getUserInfo(ServerHttpRequest request) {
        //从请求头中获取token
        String token = request.getHeaders().getFirst("token");
        if (token != null) {
            //根据token从数据库获取用户信息
            String userInfo = "";
            if (userInfo != null) {
                //将用户信息转换为对象
                AuthUserInfo authUserInfo = JSONUtil.toBean(userInfo, AuthUserInfo.class);
                return authUserInfo;
            }
            return null;
        }
        return null;
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

    private Mono<Void> out(ServerHttpResponse response, ExceptionStatusEnum exceptionStatusEnum) {
        Res result = Res.get(exceptionStatusEnum);
        byte[] bits = JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
