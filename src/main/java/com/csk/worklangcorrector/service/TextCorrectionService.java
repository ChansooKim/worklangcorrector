package com.csk.worklangcorrector.service;

import com.csk.worklangcorrector.dto.CorrectionRequest;
import com.csk.worklangcorrector.dto.CorrectionResponse;
import com.csk.worklangcorrector.repository.CorrectionDictionaryRepository;
import com.csk.worklangcorrector.repository.IncorrectTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class TextCorrectionService {

    private final CorrectionDictionaryRepository correctionRepository;
    private final IncorrectTextRepository incorrectTextRepository;

    public Mono<CorrectionResponse> correctText(CorrectionRequest request) {
        String text = request.getText();
        return Mono.fromCallable(() -> incorrectTextRepository.findByWrongText(text))
                .subscribeOn(Schedulers.boundedElastic()) // 블로킹 I/O 처리
                .flatMap(optionalIncorrectText -> {
                    if (optionalIncorrectText.isEmpty()) {
                        return Mono.just(CorrectionResponse.builder()
                                .correction(null)
                                .corrected(false)
                                .build());
                    }

                    Long correctionId = optionalIncorrectText.get().getCorrectionDictionary().getId();
                    return Mono.fromCallable(() -> correctionRepository.findById(correctionId))
                            .subscribeOn(Schedulers.boundedElastic()) // JPA 블로킹 처리
                            .flatMap(optionalCorrection ->
                                    optionalCorrection.map(correction ->
                                            Mono.just(correctionResponse(correction.getCorrectedText()))
                                    ).orElseGet(() ->
                                            Mono.just(correctionResponse(text)) // 원본 텍스트 반환
                                    )
                            );
                });
    }

    private CorrectionResponse correctionResponse(String text) {
        return CorrectionResponse.builder()
                .correction(text)
                .corrected(true)
                .build();
    }
}