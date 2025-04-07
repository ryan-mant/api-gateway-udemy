package com.ryan.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/get")
                                .filters(f ->
                                        f.addRequestHeader("Hello", "World")
                                                .addRequestParameter("Hello", "World"))
                                .uri("http://httpbin.org:80"))
                .route(r -> r.path("/api/cambio/**")
                        .uri("lb://cambio-service"))
                .route(r -> r.path("/api/book/**")
                        .uri("lb://book-service"))
                .build();
    }
}
