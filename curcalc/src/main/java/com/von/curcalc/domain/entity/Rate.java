package com.von.curcalc.domain.entity;

import com.von.curcalc.enums.CurrencyCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "exchage_rates")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true, updatable = false, nullable = false, length = 3)
    private CurrencyCode currencyCode;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal value;

    private LocalDateTime lastUpdateTimeStamp;
}
