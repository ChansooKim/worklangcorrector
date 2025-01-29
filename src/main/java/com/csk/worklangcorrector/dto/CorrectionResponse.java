package com.csk.worklangcorrector.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CorrectionResponse {
    private String correction;
    private boolean corrected;
}
