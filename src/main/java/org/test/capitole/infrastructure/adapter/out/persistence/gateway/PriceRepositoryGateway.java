package org.test.capitole.infrastructure.adapter.out.persistence.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.core.gateway.PriceGateway;
import org.test.capitole.infrastructure.adapter.out.persistence.mapper.PriceMapper;
import org.test.capitole.infrastructure.adapter.out.persistence.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;


/**
 * PriceRepositoryGateway class : Represents the adapter that uses the PriceRepository to search for prices in the database.
 */
@Slf4j
@RequiredArgsConstructor
@Component
@SuppressWarnings("java:S3864")
public class PriceRepositoryGateway implements PriceGateway {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        log.info("Searching in <Repository> the price for Product: {} Brand: {} EffectiveDate: {}", productId, brandId, effectiveDate);
        return priceRepository.findByParamsOrderPriorityDesc(productId, brandId, effectiveDate)
                                .stream()
                                .flatMap(Collection::stream)
                                .peek(price -> log.info("Price found in <Repository> - Price List: {}, Priority: {}, Value: {}, Currency: {}",
                                                         price.getPricePK().getPriceList(), price.getPriority(), price.getVPrice(),
                                                         price.getCurrency().getCurrencyIso()))
                                .findFirst()
                                .map(priceMapper::toDomain);
    }
}
