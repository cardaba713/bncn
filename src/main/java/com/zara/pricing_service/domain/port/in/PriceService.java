package com.zara.pricing_service.domain.port.in;

import com.zara.pricing_service.domain.model.PriceVP;
import com.zara.pricing_service.domain.model.PriceQuery;
import java.util.Optional;

public interface PriceService {
    Optional<PriceVP> findApplicablePrice(PriceQuery query);
}