package com.zara.pricing_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.zara.pricing_service.application.service.PriceServiceImpl;
import com.zara.pricing_service.domain.model.Price;
import com.zara.pricing_service.domain.model.PriceQuery;
import com.zara.pricing_service.domain.port.out.PriceRepository;

@SpringBootTest
class PricingServiceApplicationTests {

	@Mock
    private PriceRepository priceRepository;

    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl(priceRepository);
    }

    @Test
    void test1() {
        //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)

        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        PriceQuery query = new PriceQuery(applicationDate, 35455L, 1L);

        // Precios que podrían aplicar
        Price basePrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );

        List<Price> prices = Arrays.asList(basePrice);
        when(priceRepository.findApplicablePrices(query)).thenReturn(prices);

        // When
        Optional<Price> result = priceService.findApplicablePrice(query);

        // Then
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("35.50"), result.get().getPrice());
        assertEquals(Integer.valueOf(0), result.get().getPriority());
        assertEquals(Long.valueOf(1L), result.get().getBrandId());
        assertEquals(Long.valueOf(35455L), result.get().getProductId());
        verify(priceRepository).findApplicablePrices(query);
    }

    @Test
    void test2() {
        //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        PriceQuery query = new PriceQuery(applicationDate, 35455L, 1L);

        // Precio base
        Price basePrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );

        // Precio con mayor prioridad para este rango horario
        Price priorityPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30),
                2L, 35455L, 1, new BigDecimal("25.45"), "EUR"
        );

        List<Price> prices = Arrays.asList(basePrice, priorityPrice);
        when(priceRepository.findApplicablePrices(query)).thenReturn(prices);

        // When
        Optional<Price> result = priceService.findApplicablePrice(query);

        // Then
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("25.45"), result.get().getPrice());
        assertEquals(Integer.valueOf(1), result.get().getPriority());
        assertEquals(Long.valueOf(1L), result.get().getBrandId());
        assertEquals(Long.valueOf(35455L), result.get().getProductId());
        verify(priceRepository).findApplicablePrices(query);
    }

    @Test
    void test3() {
        //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        PriceQuery query = new PriceQuery(applicationDate, 35455L, 1L);

        // Precio base (el único que aplica a las 21:00)
        Price basePrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );

        // Precio que ya no aplica (termina a las 18:30)
        Price expiredPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30),
                2L, 35455L, 1, new BigDecimal("25.45"), "EUR"
        );

        List<Price> prices = Arrays.asList(basePrice, expiredPrice);
        when(priceRepository.findApplicablePrices(query)).thenReturn(prices);

        // When
        Optional<Price> result = priceService.findApplicablePrice(query);

        // Then
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("35.50"), result.get().getPrice());
        assertEquals(Integer.valueOf(0), result.get().getPriority());
        assertEquals(Long.valueOf(1L), result.get().getBrandId());
        assertEquals(Long.valueOf(35455L), result.get().getProductId());
        verify(priceRepository).findApplicablePrices(query);
    }

    @Test
    void test4() {
        //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        PriceQuery query = new PriceQuery(applicationDate, 35455L, 1L);

        // Precio base
        Price basePrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );

        // Precio específico para el día 15
        Price day15Price = new Price(
                1L,
                LocalDateTime.of(2020, 6, 15, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0),
                3L, 35455L, 1, new BigDecimal("30.50"), "EUR"
        );

        List<Price> prices = Arrays.asList(basePrice, day15Price);
        when(priceRepository.findApplicablePrices(query)).thenReturn(prices);

        // When
        Optional<Price> result = priceService.findApplicablePrice(query);

        // Then
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("30.50"), result.get().getPrice());
        assertEquals(Integer.valueOf(1), result.get().getPriority());
        assertEquals(Long.valueOf(1L), result.get().getBrandId());
        assertEquals(Long.valueOf(35455L), result.get().getProductId());
        verify(priceRepository).findApplicablePrices(query);
    }

    @Test
    void test5() {
        //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        PriceQuery query = new PriceQuery(applicationDate, 35455L, 1L);

        // Precio base
        Price basePrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );

        // Precio específico para el día 16 por la noche
        Price day16NightPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 15, 16, 0),
                LocalDateTime.of(2020, 6, 16, 23, 59, 59),
                4L, 35455L, 1, new BigDecimal("38.95"), "EUR"
        );

        List<Price> prices = Arrays.asList(basePrice, day16NightPrice);
        when(priceRepository.findApplicablePrices(query)).thenReturn(prices);

        // When
        Optional<Price> result = priceService.findApplicablePrice(query);

        // Then
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("38.95"), result.get().getPrice());
        assertEquals(Integer.valueOf(1), result.get().getPriority());
        assertEquals(Long.valueOf(1L), result.get().getBrandId());
        assertEquals(Long.valueOf(35455L), result.get().getProductId());
        verify(priceRepository).findApplicablePrices(query);
    }

}
