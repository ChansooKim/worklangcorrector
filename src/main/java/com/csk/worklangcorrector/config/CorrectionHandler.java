package com.csk.worklangcorrector.config;

import com.csk.worklangcorrector.common.exception.NoContentException;
import com.csk.worklangcorrector.dto.CorrectionRequest;
import com.csk.worklangcorrector.service.TextCorrectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CorrectionHandler {
    private final TextCorrectionService textCorrectionService;

    private Mono<ServerResponse> jsonResponse(Object body) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body);
    }

    public Mono<ServerResponse> word(ServerRequest request) {
        return request.bodyToMono(CorrectionRequest.class)
                .doOnNext(req ->log.info("Received word correction request: {}", req))
                .flatMap(textCorrectionService::correctWord)
                .switchIfEmpty(Mono.error(new NoContentException("No correction needed")))
                .flatMap(this::jsonResponse);
    }

    public Mono<ServerResponse> sentence(ServerRequest request) {
        return request.bodyToMono(CorrectionRequest.class)
                .doOnNext(req -> log.info("Received sentence correction request: {}", req))
                .flatMap(textCorrectionService::correctSentence)
                .switchIfEmpty(Mono.error(new NoContentException("No correction needed")))
                .flatMap(this::jsonResponse);
    }
}