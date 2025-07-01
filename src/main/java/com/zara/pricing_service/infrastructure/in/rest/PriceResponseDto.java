package com.zara.pricing_service.infrastructure.in.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponseDto {
    @Getter
    @Setter
    private Long productId;

    @Getter
    @Setter
    private Long brandId;

    @Getter
    @Setter
    private Long priceList;

    @Getter
    @Setter
    private LocalDateTime startDate;

    @Getter
    @Setter
    private LocalDateTime endDate;

    @Getter
    @Setter
    private BigDecimal price;

    public PriceResponseDto() {
    }

    public PriceResponseDto(Long productId, Long brandId, Long priceList, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
