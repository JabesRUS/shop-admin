package com.javaacademy.shop_admin.repository;

import com.javaacademy.shop_admin.entity.Shop;
import com.javaacademy.shop_admin.storage.ShopStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepository {
    private final ShopStorage shopStorage;

    public void save(Shop shop) {
        shopStorage.getStorage().put(shop.getName(), shop);
    }

    public List<Shop> getAll() {
        return shopStorage.getStorage().values().stream().toList();
    }

}
