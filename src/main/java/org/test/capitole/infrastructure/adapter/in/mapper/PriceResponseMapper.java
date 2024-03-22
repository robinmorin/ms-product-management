package org.test.capitole.infrastructure.adapter.in.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.infrastructure.adapter.in.model.PriceResponse;

import java.math.RoundingMode;

/**
 * Mapper to convert domain object PriceDomain to presentation object PriceResponse
 */
@Slf4j
@Component
public class PriceResponseMapper {
    public PriceResponse toResponse(PriceDomain priceDomain) {
        log.info("Converting Domain Price to Response Price");
        var dateRange = PriceResponse.EffectiveDateRange.builder()
                            .from(priceDomain.getEffectiveDates().getStartDate())
                            .to(priceDomain.getEffectiveDates().getEndDate())
                        .build();
        return PriceResponse.builder()
                            .productId(priceDomain.getProductId())
                            .brandId(priceDomain.getBrandId())
                            .priceListId(priceDomain.getPriceList())
                            .priceToApply(priceDomain.getPrice().setScale(2, RoundingMode.CEILING))
                            .effectiveDateRange(dateRange)
                        .build();
    }
}
