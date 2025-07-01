package com.zara.pricing_service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.pricing_service.domain.model.Price;
import com.zara.pricing_service.domain.model.PriceQuery;
import com.zara.pricing_service.domain.port.in.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PriceService priceService;

    @Test
    void test1() throws Exception {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        
        // Given
        Price expectedPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );
        
        when(priceService.findApplicablePrice(any(PriceQuery.class)))
                .thenReturn(Optional.of(expectedPrice));

        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"));
    }

    @Test
    void test2() throws Exception {
        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        
        // Given
        Price expectedPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30),
                2L, 35455L, 1, new BigDecimal("25.45"), "EUR"
        );
        
        when(priceService.findApplicablePrice(any(PriceQuery.class)))
                .thenReturn(Optional.of(expectedPrice));

        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00"));
    }

    @Test
    void test3() throws Exception {
        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        
        // Given
        Price expectedPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L, 35455L, 0, new BigDecimal("35.50"), "EUR"
        );
        
        when(priceService.findApplicablePrice(any(PriceQuery.class)))
                .thenReturn(Optional.of(expectedPrice));

        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"));
    }

    @Test
    void test4() throws Exception {
        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        
        // Given
        Price expectedPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 15, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0),
                3L, 35455L, 1, new BigDecimal("30.50"), "EUR"
        );
        
        when(priceService.findApplicablePrice(any(PriceQuery.class)))
                .thenReturn(Optional.of(expectedPrice));

        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00"));
    }

    @Test
    void test5() throws Exception {
        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        
        // Given
        Price expectedPrice = new Price(
                1L,
                LocalDateTime.of(2020, 6, 15, 16, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                4L, 35455L, 1, new BigDecimal("38.95"), "EUR"
        );
        
        when(priceService.findApplicablePrice(any(PriceQuery.class)))
                .thenReturn(Optional.of(expectedPrice));

        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"));
    }

    @Test
    void testPriceNotFound() throws Exception {
        // Test caso cuando no se encuentra precio aplicable
        
        // Given
        when(priceService.findApplicablePrice(any(PriceQuery.class)))
                .thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "99999")
                .param("brandId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testInvalidParameters() throws Exception {
        // Test caso con parámetros inválidos
        
        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "invalid-date")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMissingParameters() throws Exception {
        // Test caso con parámetros faltantes
        
        // When & Then
        mockMvc.perform(get("/api/v1/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "35455"))
                // Falta brandId
                .andExpect(status().isBadRequest());
    }

    // Tests para cobertura de DTOs
    @Test
    void testPriceResponseDtoConstructors() {
        // Test constructor vacío
        PriceResponseDto emptyDto = new PriceResponseDto();
        assertNull(emptyDto.getProductId());
        assertNull(emptyDto.getBrandId());
        assertNull(emptyDto.getPriceList());
        assertNull(emptyDto.getStartDate());
        assertNull(emptyDto.getEndDate());
        assertNull(emptyDto.getPrice());

        // Test constructor con parámetros
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        BigDecimal price = new BigDecimal("35.50");
        
        PriceResponseDto dto = new PriceResponseDto(35455L, 1L, 1L, startDate, endDate, price);
        assertEquals(35455L, dto.getProductId());
        assertEquals(1L, dto.getBrandId());
        assertEquals(1L, dto.getPriceList());
        assertEquals(startDate, dto.getStartDate());
        assertEquals(endDate, dto.getEndDate());
        assertEquals(price, dto.getPrice());
    }

    @Test
    void testPriceResponseDtoSetters() {
        PriceResponseDto dto = new PriceResponseDto();
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        BigDecimal price = new BigDecimal("35.50");

        dto.setProductId(35455L);
        dto.setBrandId(1L);
        dto.setPriceList(1L);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setPrice(price);

        assertEquals(35455L, dto.getProductId());
        assertEquals(1L, dto.getBrandId());
        assertEquals(1L, dto.getPriceList());
        assertEquals(startDate, dto.getStartDate());
        assertEquals(endDate, dto.getEndDate());
        assertEquals(price, dto.getPrice());
    }

    @Test
    void testPriceRequestDtoConstructors() {
        // Test constructor vacío
        PriceRequestDto emptyDto = new PriceRequestDto();
        assertNull(emptyDto.getApplicationDate());
        assertNull(emptyDto.getProductId());
        assertNull(emptyDto.getBrandId());

        // Test constructor con parámetros
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        PriceRequestDto dto = new PriceRequestDto(applicationDate, 35455L, 1L);
        
        assertEquals(applicationDate, dto.getApplicationDate());
        assertEquals(35455L, dto.getProductId());
        assertEquals(1L, dto.getBrandId());
    }

    @Test
    void testPriceRequestDtoSetters() {
        PriceRequestDto dto = new PriceRequestDto();
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        dto.setApplicationDate(applicationDate);
        dto.setProductId(35455L);
        dto.setBrandId(1L);

        assertEquals(applicationDate, dto.getApplicationDate());
        assertEquals(35455L, dto.getProductId());
        assertEquals(1L, dto.getBrandId());
    }
}