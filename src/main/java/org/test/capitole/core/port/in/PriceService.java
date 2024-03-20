package org.test.capitole.core.port.in;

import org.test.capitole.core.entity.PriceDomain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    PriceDomain addPrice(PriceDomain price);

    Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate);

}
