package com.zara.pricing_service.domain.port.out;

import com.zara.pricing_service.domain.model.Price;
import com.zara.pricing_service.domain.model.PriceQuery;

import java.util.List;

public interface PriceRepository {
    List<Price> findApplicablePrices(PriceQuery query);

}
