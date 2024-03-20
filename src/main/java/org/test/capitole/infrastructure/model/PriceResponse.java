package org.test.capitole.infrastructure.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PriceResponse {

    @Schema(name = "productId", description = "Product Id", example = "12345")
    private final Long productId;
    @Schema(name = "brandId", description = "Brand Id", example = "12345")
    private final Integer brandId;
    @Schema(name = "priceListId", description = "Number of Price List", example = "3")
    private final Integer priceListId;
    @Schema(name = "effectiveDateRange", description = "Range from-to date for apply price")
    private final EffectiveDateRange effectiveDateRange;
    @Schema(name = "priceToApply", description = "Price to Apply", example = "25.45")
    private final BigDecimal priceToApply;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class EffectiveDateRange {
        @Schema(name = "from", description = "Effective Date From", example = "2024-03-01T15:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime from;
        @Schema(name = "to", description = "Effective Date To", example = "2024-03-02T12:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime to;
    }

}
