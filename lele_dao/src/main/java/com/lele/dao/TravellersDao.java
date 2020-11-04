package com.lele.dao;

import com.lele.pojo.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TravellersDao {
    @Select("select * from traveller where id in(select travellerid from order_traveller where orderid=#{orderId})")
    List<Traveller> findByOrderId(String orderId);
}
