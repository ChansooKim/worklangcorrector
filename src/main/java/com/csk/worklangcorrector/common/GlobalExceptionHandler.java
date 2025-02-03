package com.csk.worklangcorrector.common;

import com.csk.worklangcorrector.common.exception.NoContentException;
import com.csk.worklangcorrector.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ServerResponse> handleNotFound(ResourceNotFoundException ex) {
        return ServerResponse.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ErrorResponse("Not Found", ex.getMessage()));
    }

    @ExceptionHandler(NoContentException.class)
    public Mono<ServerResponse> handleNoContent(NoContentException ex) {
        return ServerResponse.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(Exception.class)
    public Mono<ServerResponse> handleGenericError(Exception ex) {
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ErrorResponse("Internal Server Error", ex.getMessage()));
    }
}
