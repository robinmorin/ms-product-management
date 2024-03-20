package org.test.capitole.api.usecase.interactor.price;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.capitole.core.entity.PriceDomain;
import org.test.capitole.core.port.out.PriceRepositoryGateway;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchPriceByMostPriorityInteractor {

    private final PriceRepositoryGateway priceRepositoryGateway;

    public Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        return priceRepositoryGateway.searchByMostPriority(productId, brandId, effectiveDate);
    }

}
