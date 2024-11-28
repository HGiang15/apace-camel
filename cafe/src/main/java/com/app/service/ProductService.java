package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.model.product;

@Service
public class ProductService {

    public List<product> getAllProducts() {
        return product.read();
    }
}
