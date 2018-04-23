package com.mmall.service;

import com.mmall.common.Msg;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

public interface IProductService {

    Msg saveProduct(Product product);

    Msg setSaleStatus(Integer productId, Integer status);

    Msg manageProductDetail(Integer productId);

    Msg listProducts(String productName, int pageNo, int pageSize);

    Msg getProductDetail(Integer productId);

    Msg getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
