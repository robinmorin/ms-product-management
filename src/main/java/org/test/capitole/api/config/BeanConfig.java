package org.test.capitole.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.capitole.api.usecase.SearchPriceByMostPriorityInteractor;
import org.test.capitole.core.gateway.PriceGateway;

/***
 * Configuration class for the beans of the application for classes that couldn't be
 * annotated with Spring annotations for layer business protection
 */
@Configuration
public class BeanConfig {

    @Bean
    public SearchPriceByMostPriorityInteractor getInteractor(PriceGateway priceGateway) {
        return new SearchPriceByMostPriorityInteractor(priceGateway);
    }

}
