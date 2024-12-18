package com.javaacademy.shop_admin.mapper;

import com.javaacademy.shop_admin.dto.ShopDto;
import com.javaacademy.shop_admin.entity.Shop;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

@Service
public class ShopMapper {
    private final String NAME_PATTERN = "$.name";
    private final String STATUS_PATTERN = "$.shopStatus";
    private final String TIME_OPEN_PATTERN = "$.time_open";
    private final String TIME_CLOSE_PATTERN = "$.time_close";

    public Shop convertToEntity(String responseBody) {
        Shop shop = new Shop();
        shop.setName(JsonPath.parse(responseBody).read(NAME_PATTERN, String.class));
        shop.setStatus(JsonPath.parse(responseBody).read(STATUS_PATTERN, String.class));
        shop.setTimeOpen(JsonPath.parse(responseBody).read(TIME_OPEN_PATTERN, String.class));
        shop.setTimeClose(JsonPath.parse(responseBody).read(TIME_CLOSE_PATTERN, String.class));

        return shop;
    }

    public ShopDto convertToDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setName(shop.getName());
        shopDto.setStatus(shop.getStatus());
        shopDto.setTimeOpen(shop.getTimeOpen());
        shopDto.setTimeClose(shop.getTimeClose());
        return shopDto;
    }
}
