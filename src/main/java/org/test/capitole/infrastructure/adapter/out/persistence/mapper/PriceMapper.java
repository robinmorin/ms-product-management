package org.test.capitole.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.infrastructure.adapter.out.persistence.entity.*;

@Component
public class PriceMapper {
    public Price toEntity(PriceDomain priceDomain) {
        var product = Product.builder().productId(priceDomain.getProductId()).build();
        var brand = Brand.builder().brandId(priceDomain.getBrandId()).build();
        var currency = Currency.builder().currencyIso(priceDomain.getCurrencyIso()).build();
        var pricePk = PricePK.builder().brand(brand).product(product).priceList(priceDomain.getPriceList()).build();
        return Price.builder()
                        .pricePK(pricePk)
                        .priority(priceDomain.getPriority())
                        .currency(currency)
                        .startDate(priceDomain.getEffectiveDates().getStartDate())
                        .endDate(priceDomain.getEffectiveDates().getEndDate())
                        .vPrice(priceDomain.getPrice())
                    .build();
    }

    public PriceDomain toDomain(Price price) {
        var rangeDate = new PriceDomain.RangeDate(price.getStartDate(), price.getEndDate());
        return PriceDomain.builder()
                            .productId(price.getPricePK().getProduct().getProductId())
                            .brandId(price.getPricePK().getBrand().getBrandId())
                            .priceList(price.getPricePK().getPriceList())
                            .priority(price.getPriority())
                            .effectiveDates(rangeDate)
                            .price(price.getVPrice())
                            .currencyIso(price.getCurrency().getCurrencyIso())
                        .build();
    }
}
