package com.von.curcalc.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.von.curcalc.controller.RateController;
import com.von.curcalc.domain.dto.CalculatorResponseDto;
import com.von.curcalc.enums.CurrencyCode;
import com.von.curcalc.service.ExchangeRateService;
import com.von.curcalc.service.scheduled.ScheduledRateFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class RateControllerImpl implements RateController {
    private final ExchangeRateService exchangeRateService;
    private final ScheduledRateFetcher scheduledRateFetcher;

    @GetMapping("/exchange")
    @Override
    public ResponseEntity<CalculatorResponseDto> getCalculation(
            @RequestParam CurrencyCode from,
            @RequestParam CurrencyCode to,
            @RequestParam BigDecimal amount) {

        CalculatorResponseDto responseDto = exchangeRateService.calculateExchange(from, to, amount);

        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/exchange/update")
    @Override public ResponseEntity<Void> updateRates() throws JsonProcessingException {
        scheduledRateFetcher.fetchRates();
        return ResponseEntity.ok().build();
    }
}
