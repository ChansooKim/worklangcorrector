package com.csk.worklangcorrector.config;

import com.csk.worklangcorrector.controller.RouterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {
    private final RouterConfig routerConfig;
    private final HealthCheckHandler healthCheckHandler;
    private final CorrectionHandler correctionHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(GET("/health"), healthCheckHandler::isHealthy)
                .andRoute(GET("/"), request -> ok().bodyValue("Welcome to the home page"))
                .andRoute(POST("/api/correctWord"), correctionHandler::word)
                .andRoute(POST("/api/correctSentence"), correctionHandler::sentence);
    }
}
