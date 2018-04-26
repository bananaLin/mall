package com.mmall.controller.app;

import com.mmall.common.Msg;
import com.mmall.controller.BaseController;
import com.mmall.service.ICartService;
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
        return iCartService.addCart(userId, productId, count);
    }
}
