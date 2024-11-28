package com.app.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

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
                return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, product.class));
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
