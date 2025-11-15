package com.von.curcalc.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.von.curcalc.enums.CurrencyCode;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RateResponseDto(
        Map<CurrencyCode, BigDecimal> rates
) {
}
