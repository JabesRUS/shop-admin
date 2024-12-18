package com.javaacademy.shop_admin.storage;

import com.javaacademy.shop_admin.entity.Shop;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class ShopStorage {
    private final Map<String, Shop> storage = new HashMap<>();
}
