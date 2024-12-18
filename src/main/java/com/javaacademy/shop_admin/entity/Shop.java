package com.javaacademy.shop_admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    private String name;
    private String status;
    private String timeOpen;
    private String timeClose;
}
