package com.von.curcalc.service;

import com.von.curcalc.domain.dto.CalculatorResponseDto;
import com.von.curcalc.domain.dto.DataResponseDto;
import com.von.curcalc.enums.CurrencyCode;

import java.math.BigDecimal;

public interface ExchangeRateService {

    CalculatorResponseDto calculateExchange(CurrencyCode from, CurrencyCode to, BigDecimal amount);

    void saveOrUpdateTable(DataResponseDto dataResponseDto);
}
