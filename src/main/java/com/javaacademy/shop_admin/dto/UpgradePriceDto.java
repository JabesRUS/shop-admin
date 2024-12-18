package com.javaacademy.shop_admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgradePriceDto {
    @Schema(description = "Имя товара")
    private String name;
    @JsonProperty("new_price")
    @Schema(description = "Новая цена товара")
    private BigDecimal newPrice;
}
