package com.von.curcalc.exception;

import com.von.curcalc.domain.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleCalculatorException(CalculatorException ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.message));
    }
}
