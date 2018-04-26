package com.mmall.service.impl;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.Cart;
import com.mmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartMapper cartMapper;

    /**
     * 加入购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    @Override
    public Msg addCart(Integer userId, Integer productId, Integer count) {
        if(productId == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        if(count == null || count < 0){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        if(cart == null){
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(count);
            cartMapper.insert(cart);
        }else{
            cart.setQuantity(cart.getQuantity() + count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return Msg.createSucMsg(Result.ADD_CART_SUCCESS);
    }


}
