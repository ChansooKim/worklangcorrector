package com.csk.worklangcorrector.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HealthCheckHandler {
    public Mono<ServerResponse> isHealthy(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("OK"), String.class);
    }
}
