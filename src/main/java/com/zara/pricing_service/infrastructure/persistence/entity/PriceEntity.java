package com.zara.pricing_service.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class PriceEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "BRAND_ID", nullable = false)
    private Long brandId;

    @Getter
    @Setter
    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Getter
    @Setter
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Getter
    @Setter
    @Column(name = "PRICE_LIST", nullable = false)
    private Long priceList;

    @Getter
    @Setter
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Getter
    @Setter
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Getter
    @Setter
    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Getter
    @Setter
    @Column(name = "CURR", nullable = false, length = 3)
    private String currency;

    public PriceEntity() {}

    public PriceEntity(Long brandId, LocalDateTime startDate, LocalDateTime endDate,
                       Long priceList, Long productId, Integer priority,
                       BigDecimal price, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

}
