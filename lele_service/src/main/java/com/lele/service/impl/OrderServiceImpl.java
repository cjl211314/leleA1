package com.lele.service.impl;

import com.github.pagehelper.PageHelper;
import com.lele.dao.OrderDao;
import com.lele.pojo.Orders;
import com.lele.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //页码  每页显示条数
        PageHelper.startPage(page,size);

        return orderDao.findAll();
    }

    @Override
    public Orders findById(String orderId)throws Exception {
        return orderDao.findByOrderId(orderId);
    }
}
