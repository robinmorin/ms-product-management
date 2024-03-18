package org.test.capitole.infrastructure.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.capitole.infrastructure.adapter.model.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Tag(name = "Prices", description = "Controller for Prices Product Operations")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class PriceController {

    private final Validator validator;

    @Operation(summary = "Request price product by brand that could be apply in specific date",
                  responses = @ApiResponse(responseCode = "200",
                                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = PriceResponse.class))))
    @Parameter(name = "productId", description = "Product Id")
    @Parameter(name = "brandId", description = "Brand Id")
    @Parameter(name = "effectiveDate", description = "Date for apply requesting", example = "2024-03-01T15:00:00")
    @GetMapping("/{productId}/brand/{brandId}/price-to-apply")
    ResponseEntity<PriceResponse> getPriceToApply(@PathVariable
                                                  @NotNull(message = "ProductId must have a valid value")
                                                  @Min(value = 1, message = "ProductId must be greater than 0")
                                                  Long productId,
                                                  @PathVariable
                                                  @NotNull(message = "BrandId must have a valid value")
                                                  @Min(value = 1, message = "BrandId must be greater than 0")
                                                  Integer brandId,
                                                  @RequestParam
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
                                                  LocalDateTime effectiveDate){


        return ResponseEntity.ok(getMockResponse());

    }

    public PriceResponse getMockResponse(){
        return PriceResponse.builder()
                            .productId(35455L)
                            .brandId(1)
                            .priceListId(2)
                            .effectiveDateRange(PriceResponse.EffectiveDateRange.builder()
                                                                                .from(LocalDateTime.now().minusDays(2))
                                                                                .to(LocalDateTime.now().plusDays(3))
                                                                            .build())
                            .priceToApply(BigDecimal.valueOf(35.50))
                        .build();
    }

}
