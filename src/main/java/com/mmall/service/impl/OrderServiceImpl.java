package com.mmall.service.impl;

import com.mmall.dao.OrderMapper;
import com.mmall.pojo.Order;
import com.mmall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean addOrUpdateOrder(Integer userId, Order order) {

        if(order == null){
            return false;
        }
        order.setUserId(userId);
        int resultCount = 0;
        // 等于空为新增订单
        if(order.getId() == null){
            resultCount = orderMapper.insert(order);
        }else{ // 不为空为更新订单
            resultCount = orderMapper.updateByPrimaryKey(order);
        }
        return resultCount > 0;
    }

    @Override
    public boolean deleteOrder(Integer orderId, Integer userId) {
        if(orderId == null){
            return false;
        }
        return orderMapper.deleteByPrimaryKey(orderId) > 0;
    }
}
