package org.test.capitole.api.usecase;

import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.core.gateway.PriceGateway;

import java.time.LocalDateTime;
import java.util.Optional;


public class SearchPriceByMostPriorityInteractor {

    private final PriceGateway priceGateway;

    public SearchPriceByMostPriorityInteractor(PriceGateway priceGateway) {
        this.priceGateway = priceGateway;
    }

    public Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        return priceGateway.searchByMostPriority(productId, brandId, effectiveDate);
    }

}
