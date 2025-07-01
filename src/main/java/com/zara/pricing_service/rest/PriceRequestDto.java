package com.zara.pricing_service.rest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PriceRequestDto {

    @Getter
    @Setter
    @NotNull(message = "Application date is required")
    private LocalDateTime applicationDate;

    @Getter
    @Setter
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Getter
    @Setter
    @NotNull(message = "Brand ID is required")
    private Long brandId;

    public PriceRequestDto() {}

    public PriceRequestDto(LocalDateTime applicationDate, Long productId, Long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

}
