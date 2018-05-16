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
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

    //@Autowired
    //private  iOrderService;

    
    @RequestMapping("/add_order.do")
    @ResponseBody
    public Msg addCart(HttpSession httpSession, List<Integer> productIds, long amount){
        Integer userId = this.getCurrentUserId(httpSession);

        if(productIds == null || productIds.size() == 0){
            return Msg.createFailMsg(Result.NO_PRODUCT);
        }
        if(amount < 0){
            return Msg.createFailMsg(Result.ERROR_PARAMETER);
        }
        //iOrderService.addCart(userId, productId, count);
        return Msg.createSucMsg();
    }


   

}
