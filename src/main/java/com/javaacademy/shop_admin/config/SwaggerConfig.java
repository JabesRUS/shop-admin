package com.javaacademy.shop_admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        Info info = new Info()
                .title("Api сети магазинов")
                .contact(new Contact()
                        .name("Евгений")
                        .email("mail.mail.ru")
                        .url("www.pornohab.com"))
                .description("Это Api для сети продуктовых магазинов.");
        return new OpenAPI().info(info);
    }
}
