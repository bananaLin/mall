package com.mmall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.dao.CartMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Cart;
import com.mmall.pojo.Product;
import com.mmall.service.ICartService;
import com.mmall.util.BigDecimalUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.CartProductVo;
import com.mmall.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 加入购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    @Override
    public void addCart(Integer userId, Integer productId, Integer count) {

        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        Date time = new Date();
        if(cart == null){
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(count);
            cart.setCreateTime(time);
            cart.setUpdateTime(time);
            cart.setChecked(Const.Cart.CHECKED);
            cartMapper.insert(cart);
        }else{
            cart.setQuantity(cart.getQuantity() + count);
            cart.setUpdateTime(time);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
    }

    /**
     * 查看购物车
     * @param userId
     * @return
     */
    @Override
    public CartVo listProducts(Integer userId) {
        CartVo cartVo = new CartVo();
        List<Cart> listCart = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVos = Lists.newArrayList();
        BigDecimal cartTotalPrice = new BigDecimal("0");
        for(Cart cart : listCart){
            CartProductVo cartProductVo = new CartProductVo();
            BeanUtils.copyProperties(cart, cartProductVo);

            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            if(product != null){
                cartProductVo.setProductMainImage(product.getMainImage());
                cartProductVo.setProductName(product.getName());
                cartProductVo.setProductSubtitle(product.getSubtitle());
                cartProductVo.setProductStatus(product.getStatus());
                cartProductVo.setProductPrice(product.getPrice());
                cartProductVo.setProductStock(product.getStock());
            }
            //判断库存
            int buyLimitCount = 0;

            if(product.getStock() >= cart.getQuantity()){
                //库存充足的时候
                buyLimitCount = cart.getQuantity();
                cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
            }else{
                buyLimitCount = product.getStock();
                cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                //购物车中更新有效库存
                Cart cartForQuantity = new Cart();
                cartForQuantity.setId(cart.getId());
                cartForQuantity.setQuantity(buyLimitCount);
                cartMapper.updateByPrimaryKeySelective(cartForQuantity);
            }
            cartProductVo.setQuantity(buyLimitCount);
            //计算总价
            cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),cartProductVo.getQuantity()));
            cartProductVo.setProductChecked(cart.getChecked());

            if(cart.getChecked() == Const.Cart.CHECKED){
                //如果已经勾选,增加到整个的购物车总价中
                cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),cartProductVo.getProductTotalPrice().doubleValue());
            }
            cartProductVos.add(cartProductVo);
        }

        Integer count = cartMapper.selectCartProductCount(userId);
        cartVo.setProductCount(count);
        cartVo.setCartProductVoList(cartProductVos);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));

        return cartVo;
    }

    /**
     * 更新购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    @Override
    public boolean updateCart(Integer userId, Integer productId, Integer count) {
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        Date time = new Date();
        if(cart == null){
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setCreateTime(time);
            cart.setUpdateTime(time);
            cart.setQuantity(count);
            cartMapper.insert(cart);
        }else{
            cart.setQuantity(count);
            cart.setUpdateTime(time);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public CartVo listCart(Integer userId) {
        return listProducts(userId);
    }

    /**
     * 删除商品
     * @param userId
     * @param productIds
     * @return
     */
    @Override
    public boolean deleteCart(Integer userId, String productIds) {
        List<String> products = Splitter.on(",").splitToList(productIds);
        return cartMapper.deleteByUserIdProductIds(userId, products) > 0;
    }

    /**
     * 选中指定商品
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public CartVo selectOrUnSelect(Integer userId, Integer productId) {
        cartMapper.selectCartByUserIdProductId(userId, productId);
        return this.listProducts(userId);
    }

    /**
     * 显示购车车数量
     * @param userId
     * @return
     */
    @Override
    public Integer getCount(Integer userId) {
        return cartMapper.selectCartProductCount(userId);
    }

    /**
     * 全选
     * @param userId
     * @param productId
     * @param checked
     * @return
     */
    @Override
    public CartVo selectOrUnSelectAll(Integer userId, Integer productId, Integer checked) {
        cartMapper.checkedOrUncheckedProduct(userId,productId,checked);
        return this.listProducts(userId);
    }

    private boolean getAllCheckedStatus(Integer userId){
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;
    }


}
