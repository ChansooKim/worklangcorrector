package com.csk.worklangcorrector.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RouterConfig {

    public void logRequest(ServerRequest request) {
        System.out.println("Request received: " + request.methodName() + " " + request.path());
    }

    // 공통 헤더 제공
    public Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    // 공통 JSON 응답 빌더
    public <T> Mono<ServerResponse> jsonResponse(T body) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body);
    }
}
