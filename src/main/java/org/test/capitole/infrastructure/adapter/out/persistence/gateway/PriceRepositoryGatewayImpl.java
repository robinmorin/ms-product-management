package org.test.capitole.infrastructure.adapter.out.persistence.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.capitole.core.entity.PriceDomain;
import org.test.capitole.core.port.out.PriceRepositoryGateway;
import org.test.capitole.infrastructure.adapter.out.persistence.mapper.PriceMapper;
import org.test.capitole.infrastructure.adapter.out.persistence.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryGatewayImpl implements PriceRepositoryGateway {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PriceDomain addPrice(PriceDomain priceDomain) {
        var entity = priceMapper.toEntity(priceDomain);
        return priceMapper.toDomain(priceRepository.save(entity));
    }

    public Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        return priceRepository.findByParamsOrderPriorityDesc(productId, brandId, effectiveDate)
                                .stream()
                                .flatMap(Collection::stream)
                                .findFirst()
                                .map(priceMapper::toDomain);
    }
}
