package com.zara.pricing_service.infrastructure.persistence.adapter;

import com.zara.pricing_service.domain.model.Price;
import com.zara.pricing_service.domain.model.PriceQuery;
import com.zara.pricing_service.domain.port.out.PriceRepository;
import com.zara.pricing_service.infrastructure.persistence.entity.PriceEntity;
import com.zara.pricing_service.infrastructure.persistence.repository.PriceJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryImpl(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<Price> findApplicablePrices(PriceQuery query) {

        List<PriceEntity> entities = priceJpaRepository.findApplicablePrices(
                query.getBrandId(),
                query.getProductId(),
                query.getApplicationDate()
        );

        System.out.println("entities:"+entities.size());

        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    private Price toDomain(PriceEntity entity) {
        return new Price(
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
