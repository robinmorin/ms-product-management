package org.test.capitole.api.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.core.gateway.PriceGateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SearchPriceByMostPriorityInteractor.class})
@ExtendWith(SpringExtension.class)
class SearchPriceByMostPriorityInteractorTest {

    @MockBean
    private PriceGateway priceGateway;

    @Autowired
    private SearchPriceByMostPriorityInteractor searchPriceByMostPriorityInteractor;

    @Test
    void testNewSearchPriceByMostPriorityInteractor() {
        // Arrange and Act
        Assertions.assertDoesNotThrow(()-> new SearchPriceByMostPriorityInteractor(mock(PriceGateway.class)));
    }

    @Test
    void testSearchByMostPriority() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(4);
        LocalDateTime endDate = LocalDateTime.now();
        PriceDomain.RangeDate effectiveDates = new PriceDomain.RangeDate(startDate, endDate);

        Optional<PriceDomain> ofResult = Optional.of(PriceDomain.builder()
                                                    .brandId(1)
                                                    .productId(1L)
                                                    .priceList(1)
                                                    .priority((short) 1)
                                                    .effectiveDates(effectiveDates)
                                                    .price(new BigDecimal("22.30"))
                                                    .currencyIso("EUR")
                                                .build());
        when(priceGateway.searchByMostPriority(anyLong(), anyInt(), any(LocalDateTime.class)))
            .thenReturn(ofResult);

        // Act
        Optional<PriceDomain> actualSearchByMostPriorityResult = searchPriceByMostPriorityInteractor
                                                                .searchByMostPriority(1L, 1, LocalDateTime.now().minusHours(2));

        // Assert
        verify(priceGateway).searchByMostPriority(anyLong(), anyInt(), any(LocalDateTime.class));
        PriceDomain.RangeDate effectiveDates2 = actualSearchByMostPriorityResult.get().getEffectiveDates();
        assertSame(effectiveDates, effectiveDates2);
    }
}
