package org.test.capitole.core.port.out;

import org.test.capitole.core.entity.PriceDomain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryGateway {
    PriceDomain addPrice(PriceDomain priceDomain);
    Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate);

}
