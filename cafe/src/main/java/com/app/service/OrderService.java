package com.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.order;
import com.app.model.product;
import com.app.repository.OrderRepository;

@Service
public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    @Autowired
    public OrderService() {
    }

    // public List<order> getAllOrders() {
    // return order.read();
    // }

    // public void addOrder(order or) {
    // or.write();
    // }

    // public order getOneOrder(String idString) {
    // return order.readOne(idString);
    // }

    public void createOrder(int id, String date, int totalMoney, List<product> products) {
        for (product pro : products) {
            orderRepository.addOrder(id, date, totalMoney, (String) pro.getId());
        }
    }

    public List<order> getAllOrders() {
        List<Map<String, Object>> rows = orderRepository.getAllOrders();
        System.out.println("Raw data from DB: " + rows);
        if (rows == null || rows.isEmpty()) {
            System.out.println("No orders found in the database.");
            return new ArrayList<>(); // Trả về danh sách rỗng nếu không có dữ liệu
        }
        List<order> orders = new ArrayList<>();
        List<product> products = new ArrayList<>();
        String id = null;
        String date = null;
        int totalMoney = 0;
        Map<String, order> orderMap = new HashMap<>();
        for (Map<String, Object> row : rows) {
            if (row == null) {
                System.out.println("Found a null row in the result set, skipping...");
                continue; // Bỏ qua hàng null
            }
            id = String.valueOf(row.get("id")); // id là kiểu int
            date = (String) row.get("date"); // date là kiểu String
            totalMoney = (int) row.get("totalMoney");
            int productsJson = (int) row.get("idProduct");
            List<Map<String, Object>> productsDb = orderRepository.getProductById(productsJson);
            for (Map<String, Object> productDb : productsDb) {
                product pro = new product(String.valueOf(productDb.get("id")), (String) productDb.get("name"),
                        (int) productDb.get("price"), (String) productDb.get("currency"));
                products.add(pro);
            }
            if (orderMap.containsKey(id)) {
                order existingOrder = orderMap.get(id);
                existingOrder.getProducts().addAll(products);
            } else {
                order newOrder = new order(id, products, date, totalMoney);
                orders.add(newOrder);
                orderMap.put(id, newOrder);
            }
            products = new ArrayList<>();
        }
        return orders;
    }

    public order getOrderById(int idString) {
        List<Map<String, Object>> rows = orderRepository.getOrderById(idString);
        order orders = null;
        List<product> products = new ArrayList<>();
        String id = null;
        String date = null;
        int totalMoney = 0;
        for (Map<String, Object> row : rows) {
            if (row == null) {
                System.out.println("Found a null row in the result set, skipping...");
                continue; // Bỏ qua hàng null
            }
            id = String.valueOf(row.get("id"));
            date = (String) row.get("date");
            totalMoney = (int) row.get("totalMoney");
            int productsJson = (int) row.get("idProduct");
            List<Map<String, Object>> productsDb = orderRepository.getProductById(productsJson);
            for (Map<String, Object> productDb : productsDb) {
                product pro = new product(String.valueOf(productDb.get("id")), (String) productDb.get("name"),
                        (int) productDb.get("price"), (String) productDb.get("currency"));
                products.add(pro);
            }
        }
        orders = new order(id, products, date, totalMoney);
        return orders;
    }

    public void close() {
        orderRepository.close();
    }
}
