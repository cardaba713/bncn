package com.zara.pricing_service.infrastructure.out.persistence.adapter;

import com.zara.pricing_service.domain.model.PriceVP;
import com.zara.pricing_service.domain.model.PriceQuery;
import com.zara.pricing_service.domain.port.out.PriceRepository;
import com.zara.pricing_service.infrastructure.out.persistence.entity.PriceEntity;
import com.zara.pricing_service.infrastructure.out.persistence.repository.PriceJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryImpl(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public Optional<PriceVP> findApplicablePrices(PriceQuery query) {
        return priceJpaRepository.find(
                query.getBrandId(),
                query.getProductId(),
                query.getApplicationDate()
        ).map(this::toDomain);
    }

    private PriceVP toDomain(PriceEntity entity) {
        return new PriceVP(
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}
