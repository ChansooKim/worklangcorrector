package com.csk.worklangcorrector.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CorrectionResponse {
    private String correction;
}
