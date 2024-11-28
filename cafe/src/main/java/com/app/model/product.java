package com.app.model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class product {
    private String name;
    private String id;
    private int price;
    private String currency;

    @JsonCreator
    public product(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("price") int price,
            @JsonProperty("currency") String currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int totalMoney) {
        this.price = totalMoney;
    }

    public int getPrice() {
        return this.price;
    }

    public void setCurrency(String cuString) {
        this.currency = cuString;
    }

    public String getCurrency() {
        return this.currency;
    }

    public static List<product> read() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<product> products = null;
        try {
            products = Arrays.asList(
                    objectMapper.readValue(new File("src/main/java/com/app/data/product.json"), product[].class));
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file product.json: " + e.getMessage());
        }
        return products;
    }
}
