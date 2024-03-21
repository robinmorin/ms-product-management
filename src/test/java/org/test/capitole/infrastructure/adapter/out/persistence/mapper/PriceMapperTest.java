package org.test.capitole.infrastructure.adapter.out.persistence.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.test.capitole.core.domain.PriceDomain;
import org.test.capitole.infrastructure.adapter.out.persistence.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ContextConfiguration(classes = {PriceMapper.class})
@ExtendWith(SpringExtension.class)
class PriceMapperTest {
    @Autowired
    private PriceMapper priceMapper;


    @Test
    void testToEntity() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(4);
        LocalDateTime endDate = LocalDateTime.now();
        PriceDomain.RangeDate effectiveDates = new PriceDomain.RangeDate(startDate, endDate);

        // Act
        Price actualResult = priceMapper.toEntity(PriceDomain.builder()
                                                    .brandId(1)
                                                    .productId(1L)
                                                    .priceList(1)
                                                    .priority((short) 1)
                                                    .effectiveDates(effectiveDates)
                                                    .price(new BigDecimal("22.30"))
                                                    .currencyIso("EUR")
                                                .build());

        // Assert
        assertEquals(startDate, actualResult.getStartDate());
        assertEquals(endDate, actualResult.getEndDate());
        Currency currency = actualResult.getCurrency();
        assertEquals("EUR", currency.getCurrencyIso());
        PricePK pricePK = actualResult.getPricePK();
        Brand brand = pricePK.getBrand();
        assertNull(brand.getBrandName());
        assertNull(currency.getCurrencyName());
        Product product = pricePK.getProduct();
        assertNull(product.getProductName());
        assertNull(product.getReferencia());
        assertNull(product.getSku());
        assertEquals(new BigDecimal("22.30"), actualResult.getVPrice());
        assertEquals(1, brand.getBrandId().intValue());
        assertEquals(1, pricePK.getPriceList().intValue());
        assertEquals(1L, product.getProductId().longValue());
        assertEquals((short) 1, actualResult.getPriority().shortValue());
    }

    @Test
    void testToDomain() {
        // Arrange
        Currency currency = new Currency();
        currency.setCurrencyIso("EUR");
        currency.setCurrencyName("Euros");

        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName("Zara");

        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Varios");
        product.setReferencia("Referencia");
        product.setSku("Sku");

        PricePK pricePK = new PricePK();
        pricePK.setBrand(brand);
        pricePK.setPriceList(1);
        pricePK.setProduct(product);

        Price price = new Price();
        price.setPricePK(pricePK);
        price.setEndDate(LocalDateTime.now().plusDays(2));
        price.setStartDate(LocalDateTime.now().minusDays(1));
        price.setPriority((short) 1);
        price.setVPrice(new BigDecimal("22.30"));
        price.setCurrency(currency);

        // Act and Assert
        PriceDomain result = priceMapper.toDomain(price);
        PriceDomain.RangeDate effectiveDates = result.getEffectiveDates();
        assertSame(price.getPricePK().getBrand().getBrandId(), result.getBrandId());
        assertSame(price.getPricePK().getProduct().getProductId(), result.getProductId());
        assertSame(price.getPricePK().getPriceList(), result.getPriceList());
        assertSame(price.getPriority(), result.getPriority());
        assertSame(price.getVPrice(), result.getPrice());
        assertSame(price.getStartDate(), effectiveDates.getStartDate());
        assertSame(price.getEndDate(), effectiveDates.getEndDate());
        assertSame(price.getCurrency().getCurrencyIso(), result.getCurrencyIso());
    }

}
