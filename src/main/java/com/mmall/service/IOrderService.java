package com.mmall.service;

import com.mmall.pojo.Order;

public interface IOrderService {

    boolean addOrUpdateOrder(Integer userId, Order order);

    boolean deleteOrder(Integer orderId, Integer userId);
}
