package com.von.curcalc.service.scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.von.curcalc.domain.dto.DataResponseDto;
import com.von.curcalc.exception.CalculatorException;
import com.von.curcalc.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ScheduledRateFetcher {
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ExchangeRateService exchangeRateService;

    private final Logger logger = LoggerFactory.getLogger(ScheduledRateFetcher.class);

    @Scheduled(cron = "0 0 */1 * * *")
    public void fetchRates() throws JsonProcessingException {
        String rawJson =
                webClient.get().uri("rates?from=EUR").retrieve().bodyToMono(String.class)
                        .doOnError(e -> logger.error(e.getMessage(), e))
                        .doOnSuccess(_ -> logger.info("API Call Success!"))
                        .block();

        if (isNull(rawJson) || rawJson.isBlank()) {
            throw new CalculatorException("Failed to fetch rates.");
        }

        DataResponseDto response = objectMapper.readValue(rawJson, DataResponseDto.class);

        logger.info("Json Parse Success!");
        exchangeRateService.saveOrUpdateTable(response);
        logger.info("DB Update Success!");
    }
}
