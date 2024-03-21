package org.test.capitole.infrastructure.adapter.in.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.infrastructure.adapter.in.model.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {PriceResponseMapper.class})
@ExtendWith(SpringExtension.class)
class PriceResponseMapperTest {
    @Autowired
    private PriceResponseMapper priceResponseMapper;

    @Test
    void testToResponse() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(4);
        LocalDateTime endDate = LocalDateTime.now();
        PriceDomain.RangeDate effectiveDates = new PriceDomain.RangeDate(startDate, endDate);

        // Act
        PriceResponse actualResponse = priceResponseMapper
                .toResponse(PriceDomain.builder()
                                        .brandId(1)
                                        .productId(1L)
                                        .priceList(1)
                                        .priority((short) 1)
                                        .effectiveDates(effectiveDates)
                                        .price(new BigDecimal("22.30"))
                                        .currencyIso("EUR")
                                    .build());

        // Assert
        PriceResponse.EffectiveDateRange effectiveDateRange = actualResponse.getEffectiveDateRange();
        assertEquals(startDate, effectiveDateRange.getFrom());
        assertEquals(endDate, effectiveDateRange.getTo());
        assertEquals(1, actualResponse.getBrandId().intValue());
        assertEquals(1, actualResponse.getPriceListId().intValue());
        assertEquals(1L, actualResponse.getProductId().longValue());
        assertEquals(new BigDecimal("22.30"), actualResponse.getPriceToApply());
    }
}
