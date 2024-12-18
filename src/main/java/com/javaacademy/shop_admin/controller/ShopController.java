package com.javaacademy.shop_admin.controller;

import com.javaacademy.shop_admin.dto.ShopDto;
import com.javaacademy.shop_admin.dto.UpgradePriceDto;
import com.javaacademy.shop_admin.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Shop controller", description = "Контроллер для работы с магазинами")
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/status")
    @Operation(summary = "Получение магазинов", description = "Получение списка всех магазинов")
    public List<ShopDto> findAll() {
        return shopService.getAll();
    }

    @PatchMapping("/good")
    @Operation(summary = "Обновление цены", description = "Обновление цены товара")
    public void updateGoodPrice(@RequestBody UpgradePriceDto newPriceDto) {
        shopService.upgradePrice(newPriceDto);
    }
}
