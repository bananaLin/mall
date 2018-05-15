package com.mmall.controller.app;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private IOrderService iOrderService;

    
    @RequestMapping("/add_order.do")
    @ResponseBody
    public Msg addCart(HttpSession httpSession, List<Integer> productIds, long amount){
        Integer userId = this.getCurrentUserId(httpSession);

        if(productIds == null || productIds.size() == 0){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        if(amount == null || amount < 0){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }
        iOrderService.addCart(userId, productId, count);
        return Msg.createSucMsg();
    }

    public Msg pay(HttpSession httpSession){
    }
    
    /**
     * 列出订单
     * @param httpSession
     * @return
     */
    @RequestMapping("/list_products.do")
    @ResponseBody
    public Msg listProducts(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        CartVo cartVo = iCartService.listProducts(userId);
        return Msg.createSucMsg(cartVo);
    }

   

}
