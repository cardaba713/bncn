package com.zara.pricing_service.infrastructure.out.persistence.repository;

import com.zara.pricing_service.infrastructure.out.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceJpaRepository  extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE " +
            "p.brandId = :brandId AND " +
            "p.productId = :productId AND " +
            "p.startDate <= :applicationDate AND " +
            "p.endDate >= :applicationDate " +
            "ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> find(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("applicationDate") LocalDateTime applicationDate
    );

}
