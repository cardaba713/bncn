package com.zara.pricing_service.application.service;

import com.zara.pricing_service.domain.model.PriceVP;
import com.zara.pricing_service.domain.model.PriceQuery;
import com.zara.pricing_service.domain.port.in.PriceService;
import com.zara.pricing_service.domain.port.out.PriceRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<PriceVP> findApplicablePrice(PriceQuery query) {
        return priceRepository.findApplicablePrices(query);
    }
}
