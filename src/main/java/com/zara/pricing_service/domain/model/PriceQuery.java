package com.zara.pricing_service.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;

public class PriceQuery {
    @Getter
    private final LocalDateTime applicationDate;
    
    @Getter
    private final Long productId;
    
    @Getter
    private final Long brandId;

    public PriceQuery(LocalDateTime applicationDate, Long productId, Long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

}
