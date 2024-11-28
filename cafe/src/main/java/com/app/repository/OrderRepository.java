package com.app.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.app.database.OrderDatabase;
import com.app.model.product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class OrderRepository {
    private static final String FILE_NAME = "../data/order.json";

    public void writeProductsToFile(List<product> products) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_NAME), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<product> readProductsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                return objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, product.class));
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private OrderDatabase database;

    public OrderRepository() {
        database = new OrderDatabase();
    }
    public int addOrder(int id ,String date, int totalMoney, String productsJson) {
        return database.addOrder(id,date, totalMoney, productsJson);
    }

    public List<Map<String, Object>> getAllOrders() {
        return database.getAllOrders();
    }

    public List<Map<String, Object>> getOrderById(int idString) {
        return database.getOrderById(idString);
    }

    public List<Map<String,Object>> getProductById(int id){
        return database.getProductsById(id);
    }

    public void close() {
        database.closeConnection();
    }
}
