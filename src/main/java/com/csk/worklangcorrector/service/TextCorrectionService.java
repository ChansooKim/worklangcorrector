package com.csk.worklangcorrector.service;

import com.csk.worklangcorrector.model.CorrectionRequest;
import com.csk.worklangcorrector.model.CorrectionResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TextCorrectionService {
    public Mono<CorrectionResponse> correctText(CorrectionRequest request) {
        String text = request.getText();
        // Todo:: add logic
        return Mono.just(correctMethod(text));
    }

    private CorrectionResponse correctMethod(String text) {
        return CorrectionResponse.builder()
                .correction(text)
                .build();
    }
}
