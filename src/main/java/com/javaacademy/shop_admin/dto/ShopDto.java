package com.javaacademy.shop_admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    @Schema(description = "Название магазина")
    private String name;
    @JsonProperty("shopStatus")
    @Schema(description = "Статус магазина")
    private String status;
    @JsonProperty("time_open")
    @Schema(description = "Время открытия магазина")
    private String timeOpen;
    @JsonProperty("time_close")
    @Schema(description = "Время закрытия магазина")
    private String timeClose;

}
