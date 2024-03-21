package org.test.capitole.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PricePK implements Serializable {

    private static final long serialVersionUID = -6268951581763010640L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "BRAND_ID")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product product;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

}
