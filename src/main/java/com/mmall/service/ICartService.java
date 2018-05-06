package com.mmall.service;

import com.mmall.vo.CartVo;

public interface ICartService {

    void addCart(Integer userId, Integer productId, Integer count);

    CartVo listProducts(Integer userId);

    boolean updateCart(Integer userId, Integer productId, Integer count);

    CartVo listCart(Integer userId);

    boolean deleteCart(Integer userId, String productIds);

    CartVo selectOrUnSelectAll(Integer userId, Integer productId, Integer checkId);

    CartVo selectOrUnSelect(Integer userId, Integer productId);

    Integer getCount(Integer userId);
}
