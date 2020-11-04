package com.lele.service;

import com.lele.pojo.Orders;

import java.util.List;

public interface IOrderService {

    List<Orders> findAll(int page,int size)throws Exception;

    Orders findById(String orderId)throws Exception;
}
