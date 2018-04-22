package com.mmall.service.impl;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加/更新商品
     * @param product
     * @return
     */
    @Override
    public Msg saveProduct(Product product) {
        if(product != null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }

            Date time = new Date();
            int rowCount = 0;

            if(product.getId() != null){
                product.setUpdateTime(time);
                rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount > 0){
                    return Msg.createSucMsg(Result.UPDATE_PRODUCT_SUCCESS);
                }
            }else{
                product.setUpdateTime(time);
                product.setCreateTime(time);
                rowCount = productMapper.insert(product);
                if(rowCount > 0){
                    return Msg.createSucMsg(Result.SAVE_PRODUCT_SUCCESS);
                }
            }

        }
        return Msg.createSucMsg(Result.SAVE_PRODUCT_FAIL);
    }

    /**
     * 修改商品销售状态
     * @param productId
     * @param status
     * @return
     */
    public Msg setSaleStatus(Integer productId, Integer status){
        if(productId == null || status == null){
            return Msg.createFailMsg(Result.PRODUCT_OR_STATUS_IS_NULL);
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return Msg.createSucMsg(Result.UPDATE_PRODUCT_STATUS_SUCCESS);
        }
        return Msg.createFailMsg(Result.UPDATE_PRODUCT_STATUS_FAIL);
    }
}
