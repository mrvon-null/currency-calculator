package com.von.curcalc.service.impl;

import com.von.curcalc.domain.dto.CalculatorResponseDto;
import com.von.curcalc.domain.dto.DataResponseDto;
import com.von.curcalc.domain.entity.Rate;
import com.von.curcalc.enums.CurrencyCode;
import com.von.curcalc.exception.CalculatorException;
import com.von.curcalc.repository.RateRepository;
import com.von.curcalc.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final RateRepository rateRepository;
    private final Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    @Transactional
    @Override
    public void saveOrUpdateTable(DataResponseDto dataResponseDto) {
        Map<CurrencyCode, BigDecimal> rates = dataResponseDto.data().rates();

        rates.forEach((currency, value) -> {
            Rate rate = rateRepository.findRateByCurrencyCode(currency)
                    .map(existing -> {
                        logger.info("Updating rate for currency: {}", currency);
                        existing.setLastUpdateTimeStamp(LocalDateTime.now());
                        existing.setValue(value);
                        return existing;
                    })
                    .orElseGet(
                            () ->
                            {
                                logger.info("Creating new rate for currency: {}", currency);
                                return Rate.builder()
                                        .currencyCode(currency)
                                        .value(value)
                                        .lastUpdateTimeStamp(LocalDateTime.now())
                                        .build();
                            }
                    );
            rateRepository.save(rate);
        });
    }

    @Override
    public CalculatorResponseDto calculateExchange(CurrencyCode from, CurrencyCode to,
            BigDecimal amount) {
        Rate fromRate = rateRepository.findRateByCurrencyCode(from)
                .orElseThrow(() -> new CalculatorException("Rate not found for currency: " + from));
        Rate toRate = rateRepository.findRateByCurrencyCode(to)
                .orElseThrow(() -> new CalculatorException("Rate not found for currency: " + to));

        BigDecimal convertedAmount = amount
                .multiply(toRate.getValue())
                .divide(fromRate.getValue(), 4, RoundingMode.HALF_DOWN);

        return new CalculatorResponseDto(convertedAmount);
    }
}
