package org.test.capitole.infrastructure.adapter.out.persistence.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.core.gateway.PriceGateway;
import org.test.capitole.infrastructure.adapter.out.persistence.mapper.PriceMapper;
import org.test.capitole.infrastructure.adapter.out.persistence.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PriceRepositoryGateway implements PriceGateway {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        return priceRepository.findByParamsOrderPriorityDesc(productId, brandId, effectiveDate)
                                .stream()
                                .flatMap(Collection::stream)
                                .findFirst()
                                .map(priceMapper::toDomain);
    }
}
