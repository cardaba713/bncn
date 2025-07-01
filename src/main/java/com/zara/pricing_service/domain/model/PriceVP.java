package com.zara.pricing_service.domain.model;

import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceVP {
    @Getter
    private final Long brandId;

    @Getter
    private final LocalDateTime startDate;

    @Getter
    private final LocalDateTime endDate;

    @Getter
    private final Long priceList;

    @Getter
    private final Long productId;

    @Getter
    private final Integer priority;

    @Getter
    private final BigDecimal price;

    @Getter
    private final String currency;

    public PriceVP(Long brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long productId, Integer priority, BigDecimal price, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public boolean isApplicableAt(LocalDateTime applicationDate) {
        return !applicationDate.isBefore(startDate) && !applicationDate.isAfter(endDate);
    }
}
