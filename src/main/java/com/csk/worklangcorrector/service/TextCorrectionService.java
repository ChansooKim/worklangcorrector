package com.csk.worklangcorrector.service;

import com.csk.worklangcorrector.dto.CorrectionRequest;
import com.csk.worklangcorrector.dto.CorrectionResponse;
import com.csk.worklangcorrector.model.CorrectionDictionary;
import com.csk.worklangcorrector.repository.CorrectionDictionaryRepository;
import com.csk.worklangcorrector.repository.IncorrectTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class TextCorrectionService {
    private final CorrectionDictionaryRepository correctionRepository;
    private final IncorrectTextRepository incorrectTextRepository;

    public Mono<CorrectionResponse> correctWord(CorrectionRequest request) {
        String word = request.getText();
        return Mono.fromCallable(() -> incorrectTextRepository.findByWrongText(word))
                .subscribeOn(Schedulers.boundedElastic()) // 블로킹 I/O 처리
                .flatMap(incorrectText -> {
                    if (incorrectText.isEmpty()) {
                        return Mono.just(CorrectionResponse.builder()
                                .correction(null)
                                .corrected(false)
                                .build());
                    }

                    Long correctionId = incorrectText.get().getCorrectionDictionary().getId();
                    return Mono.fromCallable(() -> correctionRepository.findById(correctionId))
                            .subscribeOn(Schedulers.boundedElastic()) // JPA 블로킹 처리
                            .flatMap(optionalCorrection ->
                                    optionalCorrection.map(correction ->
                                            Mono.just(correctionResponse(correction.getCorrectedText()))
                                    ).orElseGet(() ->
                                            Mono.just(correctionResponse(word)) // 원본 텍스트 반환
                                    )
                            );
                });
    }

    public Mono<CorrectionResponse> correctSentence(CorrectionRequest request) {
        String sentence = request.getText();
        String[] words = sentence.split("(?<=\\s)|(?=\\s)|(?=\\p{Punct})|(?<=\\p{Punct})");
        return Flux.fromArray(words)
            .concatMap(word -> {        // 문장 순서 유지
                if(word.trim().isEmpty()) {
                    return Mono.just(word);    // 공백 유지
                }
                // 잘못된 단어 조회
                return Mono.fromCallable(() -> incorrectTextRepository.findByWrongText(word))
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(optionalIncorrectText ->
                        optionalIncorrectText.map(incorrectText ->
                            Mono.fromCallable(() ->
                                correctionRepository.findById(incorrectText.getCorrectionDictionary().getId()))
                                    .subscribeOn(Schedulers.boundedElastic())
                                    .map(optCorrectionDict ->
                                        optCorrectionDict.map(CorrectionDictionary::getCorrectedText)
                                                .orElse(word)
                                    )
                        )
                        .orElse(Mono.just(word)));
            })
            .collectList()
            .map(correctedWords -> correctionResponse(String.join("", correctedWords)));
    }

    private CorrectionResponse correctionResponse(String text) {
        return CorrectionResponse.builder()
                .correction(text)
                .corrected(true)
                .build();
    }
}