package org.test.capitole.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.capitole.api.usecase.SearchPriceByMostPriorityInteractor;
import org.test.capitole.core.gateway.PriceGateway;

@Configuration
public class BeanConfig {

    @Bean
    public SearchPriceByMostPriorityInteractor getInteractor(PriceGateway priceGateway) {
        return new SearchPriceByMostPriorityInteractor(priceGateway);
    }

}
