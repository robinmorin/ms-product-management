package org.test.capitole.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import org.test.capitole.core.entity.PriceDomain;
import org.test.capitole.infrastructure.model.PriceResponse;

import java.math.RoundingMode;

@Component
public class PriceResponseMapper {
    public PriceResponse toResponse(PriceDomain priceDomain) {
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
