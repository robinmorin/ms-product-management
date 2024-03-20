package org.test.capitole.infrastructure.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@Builder
@Validated
@AllArgsConstructor
public class PriceRequest {

    @NotNull(message = "ProductId must have a valid value")
    @Min(value = 1, message = "ProductId must be greater than 0")
    private final Long productId;

    @NotNull(message = "BrandId must have a valid value")
    @Min(value = 1, message = "BrandId must be greater than 0")
    private final Integer brandId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime effectiveDate;
}
