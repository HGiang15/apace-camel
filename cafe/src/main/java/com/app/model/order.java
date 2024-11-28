package com.app.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class order {
    private String id;
    private String date;
    private int totalMoney;
    private ArrayList<product> products;

    @JsonCreator
    public order(
        @JsonProperty("id") String id,
        @JsonProperty("date") String date,
        @JsonProperty("totalMoney") int totalMoney,
        @JsonProperty("products") ArrayList<product> products) {
        this.id = id;
        this.date = date;
        this.totalMoney = totalMoney;
        this.products = products;
    }

    public order() {
    }

    public order(String id, ArrayList<product> products, String date, int totalMoney) {
        this.id = id;
        this.products = products;
        this.date = date;
        this.totalMoney = totalMoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ArrayList<product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<product> products) {
        this.products = products;
    }

    public void write() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<order> orders = new ArrayList<>();

        try {
            File file = new File("src\\main\\java\\com\\app\\data\\order.json");
            if (file.exists() && file.length() > 0) {
                orders = objectMapper.readValue(file, TypeFactory.defaultInstance().constructCollectionType(List.class, order.class));
            }

            orders.add(this);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, orders);
            System.out.println("Đơn hàng đã được thêm vào file order.json");

        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public static List<order> read() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/main/java/com/app/data/order.json");
            List<order> orders = Arrays.asList(objectMapper.readValue(file, order[].class));
            return orders;
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            return null;
        }
    }
    public static order readOne(String idString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/main/java/com/app/data/order.json");
            List<order> orders = Arrays.asList(objectMapper.readValue(file, order[].class));
            order or = new order();
            for (order order : orders) {
                if(order.id.equals(idString)){
                    or = order;
                }
            }
            return or;
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            return null;
        }
    }
}
