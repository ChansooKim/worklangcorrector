package com.csk.worklangcorrector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouteConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(HealthCheckHandler healthCheckHandler,
                                                 TextCorrectionHandler textCorrectionHandler) {
        return RouterFunctions
                .route(GET("/health"), healthCheckHandler::isHealthy)
                .andRoute(GET("/"), request -> ok().bodyValue("Welcome to the home page"))
                .andRoute(POST("/api/correctText"), textCorrectionHandler::correctText);
    }
}
