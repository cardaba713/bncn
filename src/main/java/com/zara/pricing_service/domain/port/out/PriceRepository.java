package com.zara.pricing_service.domain.port.out;

import com.zara.pricing_service.domain.model.PriceVP;
import com.zara.pricing_service.domain.model.PriceQuery;
import org.springframework.context.annotation.Bean;
import java.util.Optional;

public interface PriceRepository {
    @Bean
    Optional<PriceVP> findApplicablePrices(PriceQuery query);

}
