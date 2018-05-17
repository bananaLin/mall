package com.mmall.service;

import com.mmall.pojo.Order;

import java.util.List;

public interface IOrderService {

    boolean addOrUpdateOrder(Integer userId, Order order);

    boolean deleteOrder(Integer orderId, Integer userId);

    List<Order> listOrder(Integer userId);

    List<Order> listPrePayOrder(Integer userId);
}
