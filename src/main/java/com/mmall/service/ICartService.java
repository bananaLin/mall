package com.mmall.service;

import com.mmall.common.Msg;

public interface ICartService {

    Msg addCart(Integer userId, Integer productId, Integer count);
}
