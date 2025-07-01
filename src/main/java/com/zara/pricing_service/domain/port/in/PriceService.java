package com.zara.pricing_service.domain.port.in;

import com.zara.pricing_service.domain.model.Price;
import com.zara.pricing_service.domain.model.PriceQuery;

import java.util.Optional;

public interface PriceService {
    Optional<Price> findApplicablePrice(PriceQuery query);
}