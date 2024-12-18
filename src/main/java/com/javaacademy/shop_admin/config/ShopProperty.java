package com.javaacademy.shop_admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class ShopProperty {
    private ArrayList<String> baseUrls;

}
