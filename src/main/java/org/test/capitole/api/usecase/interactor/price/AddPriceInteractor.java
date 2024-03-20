package org.test.capitole.api.usecase.interactor.price;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.capitole.core.entity.PriceDomain;
import org.test.capitole.core.port.out.PriceRepositoryGateway;

@Component
@RequiredArgsConstructor
public class AddPriceInteractor {

    private final PriceRepositoryGateway priceRepositoryGateway;

    public PriceDomain addPrice(PriceDomain priceDomain) {
        return priceRepositoryGateway.addPrice(priceDomain);
    }

}
