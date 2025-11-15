package com.von.curcalc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.von.curcalc.domain.dto.CalculatorResponseDto;
import com.von.curcalc.enums.CurrencyCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;


public interface RateController {

    @Operation(summary = "Manually trigger exchange rate update",
            description = "The scheduler automatically updates exchange rates every hour. " +
                    "This endpoint allows for manual triggering of the update process.")
    ResponseEntity<Void> updateRates() throws JsonProcessingException;

    @Operation(
            summary = "Calculate currency exchange",
            description = "Converts a given amount from one currency to another using the stored exchange rates."
    )
    ResponseEntity<CalculatorResponseDto> getCalculation(
            CurrencyCode from,
            CurrencyCode to,
            BigDecimal amount);
}
