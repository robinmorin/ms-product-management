package org.test.capitole.infrastructure.adapter.out.persistence.gateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.infrastructure.adapter.out.persistence.entity.*;
import org.test.capitole.infrastructure.adapter.out.persistence.mapper.PriceMapper;
import org.test.capitole.infrastructure.adapter.out.persistence.repository.PriceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PriceRepositoryGateway.class})
@ExtendWith(SpringExtension.class)
class PriceRepositoryGatewayTest {

    @MockBean
    private PriceMapper priceMapper;

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PriceRepositoryGateway priceRepositoryGateway;

    @Test
    void testSearchByMostPrioritySuccess() {

        // Arrange
        PricePK pricePK = new PricePK(Brand.builder().brandId(1).build(),
                                      Product.builder().productId(1L).build(), 1);
        Price price = Price.builder()
                .pricePK(pricePK)
                .priority((short) 1)
                .startDate(LocalDateTime.now().minusDays(4))
                .endDate(LocalDateTime.now().plusDays(3))
                .vPrice(new BigDecimal("22.30"))
                .currency(Currency.builder().currencyIso("EUR").build())
                .build();

        Optional<List<Price>> result = Optional.of(List.of(price));
        when(priceRepository.findByParamsOrderPriorityDesc(anyLong(), anyInt(), any(LocalDateTime.class))).thenReturn(result);
        PriceDomain resultMapped = PriceDomain.builder()
                .productId(1L)
                .brandId(1)
                .priority((short) 1)
                .effectiveDates(new PriceDomain.RangeDate(LocalDateTime.now().minusDays(4), LocalDateTime.now().plusDays(3)))
                .price(new BigDecimal("22.30"))
                .currencyIso("EUR")
                .build();
        when(priceMapper.toDomain(any(Price.class))).thenReturn(resultMapped);

        // Act
        Optional<PriceDomain> actualResult =
                priceRepositoryGateway.searchByMostPriority(1L, 1, LocalDateTime.now().minusHours(3));

        // Assert
        verify(priceRepository).findByParamsOrderPriorityDesc(anyLong(), anyInt(), any(LocalDateTime.class));
        assertTrue(actualResult.isPresent());
        assertEquals(resultMapped, actualResult.get());
    }

    @Test
    void testSearchByMostPriorityEmpty() {

        // Arrange
        Optional<List<Price>> result = Optional.of(new ArrayList<>());
        when(priceRepository.findByParamsOrderPriorityDesc(anyLong(), anyInt(), any(LocalDateTime.class))).thenReturn(result);

        // Act
        Optional<PriceDomain> actualSearchByMostPriorityResult =
                priceRepositoryGateway.searchByMostPriority(1L, 1, LocalDateTime.now().minusHours(3));

        // Assert
        verify(priceRepository).findByParamsOrderPriorityDesc(anyLong(), anyInt(), any(LocalDateTime.class));
        assertFalse(actualSearchByMostPriorityResult.isPresent());
    }
}
