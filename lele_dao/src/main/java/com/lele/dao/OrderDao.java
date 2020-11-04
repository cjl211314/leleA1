package com.lele.dao;

import com.lele.pojo.Member;
import com.lele.pojo.Orders;
import com.lele.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface OrderDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.lele.dao.ProductDao.findById"))
    })
    List<Orders> findAll()throws Exception;

    @Select("select * from orders where id=#{orderId}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "PRODUCTId",property = "product",javaType = Product.class,one = @One(select = "com.lele.dao.ProductDao.findById")),
            @Result(column = "MEMBERID",property = "member",javaType = Member.class,one = @One(select = "com.lele.dao.MemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select = "com.lele.dao.TravellersDao.findByOrderId"))
    })
    Orders findByOrderId(String orderId)throws Exception;
}
