package com.mmall.service.impl;

import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public boolean add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        return shippingMapper.insert(shipping) > 0;
    }

    @Override
    public boolean delete(Integer userId, Integer shippingId) {
        return shippingMapper.deleteByShippingIdUserId(userId, shippingId) > 0;
    }

    @Override
    public boolean deleteAll(Integer userId) {
        return shippingMapper.deleteByUserId(userId) > 0;
    }

    @Override
    public boolean update(Shipping shipping) {
        return shippingMapper.updateByPrimaryKeySelective(shipping) > 0;
    }

    @Override
    public Shipping get(Integer userId, Integer shippingId) {
        if(shippingId == null){
            return null;
        }
        return shippingMapper.selectByShippingIdUserId(userId, shippingId);
    }

    @Override
    public List<Shipping> list(Integer userId) {
        return shippingMapper.selectByUserId(userId);
    }
}
