package org.test.capitole.api.usecase.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.test.capitole.api.usecase.interactor.price.AddPriceInteractor;
import org.test.capitole.api.usecase.interactor.price.SearchPriceByMostPriorityInteractor;
import org.test.capitole.core.entity.PriceDomain;
import org.test.capitole.core.port.in.PriceService;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceimpl implements PriceService {

    private final AddPriceInteractor addPriceInteractor;
    private final SearchPriceByMostPriorityInteractor searchPriceByMostPriorityInteractor;

    public PriceDomain addPrice(PriceDomain priceDomain) {
        log.info("Adding new price to Product: {} Brand: {}", priceDomain.getProductId(), priceDomain.getBrandId());
        var result = addPriceInteractor.addPrice(priceDomain);
        log.info("Price added to Product: {} Brand: {} sucessfully", priceDomain.getProductId(), priceDomain.getBrandId());
        return result;
    }

    public Optional<PriceDomain> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        log.info("Searching price for Product: {} Brand: {} EffectiveDate: {}", productId, brandId, effectiveDate);
        var result = searchPriceByMostPriorityInteractor.searchByMostPriority(productId, brandId, effectiveDate);
        if(result.isPresent()) {
            log.info("Price found in Price List: {}, Value: {}, Currency: {}", result.get().getPriceList(), result.get().getPrice(), result.get().getCurrencyIso());
        } else {
            log.info("Price not found with parameters are given");
        }
        return result;
    }

}
