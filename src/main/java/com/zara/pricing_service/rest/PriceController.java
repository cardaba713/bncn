package com.zara.pricing_service.rest;

import com.zara.pricing_service.domain.model.Price;
import com.zara.pricing_service.domain.model.PriceQuery;
import com.zara.pricing_service.domain.port.in.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController (PriceService priceService){
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getApplicablePrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        PriceQuery query = new PriceQuery(applicationDate, productId, brandId);

        Optional<Price> price = priceService.findApplicablePrice(query);

        if (price.isPresent()) {
            PriceResponseDto response = mapToDto(price.get());
            return ResponseEntity.ok(response);
        } else {
            System.out.println("NOTfound");
            return ResponseEntity.notFound().build();
        }
    }

    private PriceResponseDto mapToDto(Price price) {
        return new PriceResponseDto(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );
    }
}
