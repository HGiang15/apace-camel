package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.model.order;

@Service
public class OrderService {
    public List<order> getAllOrders(){
        return order.read();
    }
    public void addOrder(order or){
        or.write();
    }
    public order getOneOrder(String idString){
        return order.readOne(idString);
    }
}
