package com.csk.worklangcorrector.config;

import com.csk.worklangcorrector.dto.CorrectionRequest;
import com.csk.worklangcorrector.dto.CorrectionResponse;
import com.csk.worklangcorrector.service.TextCorrectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TextCorrectionHandler {

    private final TextCorrectionService service;

    public Mono<ServerResponse> correctText(ServerRequest request) {
        return request.bodyToMono(CorrectionRequest.class)
                .flatMap(service::correctText)
                .flatMap(response->ServerResponse.ok().bodyValue(CorrectionResponse.class));
    }

}
