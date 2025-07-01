package com.zara.pricing_service.domain.model;

import java.time.LocalDateTime;

public class PriceQuery {
    private final LocalDateTime applicationDate;
    private final Long productId;
    private final Long brandId;

    public PriceQuery(LocalDateTime applicationDate, Long productId, Long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getBrandId() {
        return brandId;
    }
}
