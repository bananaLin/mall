package com.mmall.service;

import com.mmall.common.Msg;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

public interface ProductService {

    Msg saveProduct(Product product);

    Msg setSaleStatus(Integer productId, Integer status);
}
