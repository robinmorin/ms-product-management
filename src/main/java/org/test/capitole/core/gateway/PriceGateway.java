package org.test.capitole.core.gateway;

import org.test.capitole.core.domain.PriceDomain;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Gateway (port) for connect to repository for search prices
 */
public interface PriceGateway {
     Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate);

}
