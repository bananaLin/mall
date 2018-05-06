package com.mmall.controller.app;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.service.ICartService;
import com.mmall.vo.CartVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController extends BaseController{

    @Autowired
    private ICartService iCartService;

    @RequestMapping("add_cart.do")
    @ResponseBody
    public Msg addCart(HttpSession httpSession, Integer productId, Integer count){
        Integer userId = this.getCurrentUserId(httpSession);

        if(productId == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        if(count == null || count < 0){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }
        iCartService.addCart(userId, productId, count);
        return Msg.createSucMsg();
    }

    /**
     * 列出购物车里所有商品
     * @param httpSession
     * @return
     */
    @RequestMapping("list_products.do")
    @ResponseBody
    public Msg listProducts(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        CartVo cartVo = iCartService.listProducts(userId);
        return Msg.createSucMsg(cartVo);
    }

    @RequestMapping("update_cart.do")
    @ResponseBody
    public Msg updateCart(HttpSession httpSession, Integer productId, Integer count){
        Integer userId = this.getCurrentUserId(httpSession);

        if(productId == null){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        if(count == null || count < 0){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }
        boolean result = iCartService.updateCart(userId, productId, count);
        return Msg.createSucMsg(result);
    }

    @RequestMapping("list_cart.do")
    @ResponseBody
    public Msg list(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        CartVo cartVo = iCartService.listCart(userId);
        return Msg.createSucMsg(cartVo);
    }

    /**
     * 在购物车删除商品
     * @param httpSession
     * @param productIds
     * @return
     */
    @RequestMapping("delete_cart.do")
    @ResponseBody
    public Msg delete_cart(HttpSession httpSession, String productIds){
        Integer userId = this.getCurrentUserId(httpSession);
        if(StringUtils.isBlank(productIds)){
            return Msg.createSucMsg(Result.ERROR_PARAMETER);
        }
        boolean result = iCartService.deleteCart(userId, productIds);
        return Msg.createSucMsg(result);
    }

    /**
     * 全选中/取消全选
     * @param httpSession
     * @return
     */
    @RequestMapping("select_all.do")
    @ResponseBody
    public Msg selectAll(HttpSession httpSession, Integer checked){
        Integer userId = this.getCurrentUserId(httpSession);
        if(checked == null){
            return Msg.createSucMsg(Result.ERROR_PARAMETER);
        }
        CartVo cartVo = iCartService.selectOrUnSelectAll(userId, null, checked);
        return Msg.createSucMsg(cartVo);
    }

    /**
     * 选指定商品
     * @param httpSession
     * @return
     */
    @RequestMapping("select.do")
    @ResponseBody
    public Msg select(HttpSession httpSession, Integer productId){
        Integer userId = this.getCurrentUserId(httpSession);
        if(productId == null){
            return Msg.createSucMsg(Result.ERROR_PARAMETER);
        }
        CartVo cartVo = iCartService.selectOrUnSelect(userId, productId);
        return Msg.createSucMsg(cartVo);
    }

    /**
     * 统计购车车商品数量
     * @param httpSession
     * @return
     */
    @RequestMapping("get_count.do")
    @ResponseBody
    public Msg getCount(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        Integer count = iCartService.getCount(userId);
        return Msg.createSucMsg(count);
    }

}
