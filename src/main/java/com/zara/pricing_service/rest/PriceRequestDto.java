package com.zara.pricing_service.rest;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PriceRequestDto {

    @NotNull(message = "Application date is required")
    private LocalDateTime applicationDate;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Brand ID is required")
    private Long brandId;

    public PriceRequestDto() {}

    public PriceRequestDto(LocalDateTime applicationDate, Long productId, Long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
