package com.lele.service.impl;

import com.lele.dao.ProductDao;
import com.lele.pojo.Product;
import com.lele.service.IProductService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
    productDao.save(product);
    }
}
