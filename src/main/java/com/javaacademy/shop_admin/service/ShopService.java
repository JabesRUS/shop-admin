package com.javaacademy.shop_admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.shop_admin.config.ShopProperty;
import com.javaacademy.shop_admin.dto.ShopDto;
import com.javaacademy.shop_admin.dto.UpgradePriceDto;
import com.javaacademy.shop_admin.mapper.ShopMapper;
import com.javaacademy.shop_admin.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final static String JSON_MESSAGE_EXCEPTION = "Ошибка преобразования: %s";
    private final static String HTTP_MESSAGE_EXCEPTION = "Ошибка во время Http запроса: %s";
    private final static String RESPONSE_MESSAGE_EXCEPTION = "Ошибка запроса: %s - %s.";
    private final static String GOOD_PATH = "/shop/good";
    private final static String STATUS_PATH = "/application/status";

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;
    private final ShopProperty shopProperty;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public void upgradePrice(UpgradePriceDto newPriceDto) {
        RequestBody requestBody = createRequestBody(newPriceDto);
        shopProperty.getBaseUrls().stream()
                .map(baseUrl -> createPatchRequest(baseUrl, requestBody))
                .forEach(this::callRequest);

    }

    public List<ShopDto> getAll() {
        saveAllShop();
        return shopRepository.getAll().stream()
                .map(shopMapper::convertToDto)
                .toList();
    }

    private void saveAllShop() {
        shopProperty.getBaseUrls().stream()
                .map(this::createGetRequest)
                .forEach(this::save);
    }

    private Request createGetRequest(String baseUrl) {
        String url = baseUrl.concat(STATUS_PATH);
        return new Request.Builder()
                .get()
                .url(url)
                .build();
    }

    private void save(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null && response.isSuccessful()) {
                shopRepository.save(shopMapper.convertToEntity(response.body().string()));
            } else {
                throw new RuntimeException(RESPONSE_MESSAGE_EXCEPTION.formatted(response.code(), response.message()));
            }
        } catch (IOException e) {
            throw new RuntimeException(HTTP_MESSAGE_EXCEPTION.formatted(e.getMessage()));
        }
    }

    private RequestBody createRequestBody(UpgradePriceDto upgradePriceDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        try {
            String jsonString = objectMapper.writeValueAsString(upgradePriceDto);
            return RequestBody.create(jsonString, mediaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(JSON_MESSAGE_EXCEPTION.formatted(e.getMessage()));
        }
    }

    private Request createPatchRequest(String baseUrl, RequestBody requestBody) {

        String url = baseUrl.concat(GOOD_PATH);
        return new Request.Builder()
                .patch(requestBody)
                .url(url)
                .build();
    }

    private void callRequest(Request request) {
        try {
            client.newCall(request).execute().close();
        } catch (IOException e) {
            throw new RuntimeException(HTTP_MESSAGE_EXCEPTION.formatted(e.getMessage()));
        }
    }
}
