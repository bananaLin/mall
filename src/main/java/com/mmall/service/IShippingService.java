package com.mmall.service;

import com.mmall.pojo.Shipping;
import java.util.List;

public interface IShippingService {

    boolean add(Integer userId, Shipping shipping);

    boolean delete(Integer userId,Integer shippingId);

    boolean deleteAll(Integer userId);

    boolean update(Shipping shipping);

    Shipping get(Integer userId, Integer shippingId);

    List<Shipping> list(Integer userId);
}
