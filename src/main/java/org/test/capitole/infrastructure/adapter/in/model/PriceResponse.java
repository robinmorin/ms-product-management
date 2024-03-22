package org.test.capitole.infrastructure.adapter.in.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * PriceResponse class: Represent the presentation object to return price for the API.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {

    @Schema(name = "productId", description = "Product Id", example = "12345")
    private Long productId;
    @Schema(name = "brandId", description = "Brand Id", example = "12345")
    private Integer brandId;
    @Schema(name = "priceListId", description = "Number of Price List", example = "3")
    private Integer priceListId;
    @Schema(name = "effectiveDateRange", description = "Range from-to date for apply price")
    private EffectiveDateRange effectiveDateRange;
    @Schema(name = "priceToApply", description = "Price to Apply", example = "25.45")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private BigDecimal priceToApply;

    public BigDecimal getPriceToApply() {
        return priceToApply.setScale(2);
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    public static class EffectiveDateRange {
        @Schema(name = "from", description = "Effective Date From", example = "2024-03-01 15:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime from;
        @Schema(name = "to", description = "Effective Date To", example = "2024-03-02 12:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime to;
    }

    public String toString() {
        return "PriceResponse(productId=" + this.getProductId() + ", brandId=" + this.getBrandId() + ", priceListId=" + this.getPriceListId() + ", effectiveDateRange=" + this.getEffectiveDateRange() + ", priceToApply=" + this.getPriceToApply() + ")";
    }

}
