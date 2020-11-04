package com.lele.service;

import com.lele.pojo.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);
}