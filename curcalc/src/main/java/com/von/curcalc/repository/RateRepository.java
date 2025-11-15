package com.von.curcalc.repository;

import com.von.curcalc.domain.entity.Rate;
import com.von.curcalc.enums.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findRateByCurrencyCode(CurrencyCode currencyCode);
}
