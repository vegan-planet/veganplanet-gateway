package com.veganplanet.gateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义路由规则
 *
 * @date 2023/12/21 22:04
 */
@Configuration
public class RoutesConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("veganplanet-system-routes",
                        r -> r.path("/system/**")
                                .filters(f -> f
                                        .stripPrefix(1)
                                )
                                .uri("lb://veganplanet-system/"))
                .route("veganplanet-user-route",
                        r -> r.path("/user/**")
                                .filters(f -> f
                                        .stripPrefix(1)
                                )
                                .uri("lb://veganplanet-user/"))
                .route("veganplanet-base-route",
                        r -> r.path("/base/**")
                                .filters(f -> f
                                        .stripPrefix(1)
                                )
                                .uri("lb://veganplanet-base/"))
                .build();
    }
}
