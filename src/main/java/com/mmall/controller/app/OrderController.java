package com.mmall.controller.app;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.pojo.Order;
import com.mmall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private IOrderService iOrderService;
    
    @RequestMapping("/add_order.do")
    @ResponseBody
    public Msg addCart(HttpSession httpSession, Order order){
        Integer userId = this.getCurrentUserId(httpSession);
        if(order == null){
            return Msg.createSucMsg(Result.ERROR_PARAMETER);
        }
        iOrderService.addOrUpdateOrder(userId, order);
        return Msg.createSucMsg();
    }

    @RequestMapping("/pay.do")
    @ResponseBody
    public Msg pay(HttpSession httpSession, Order order){
        return null;
    }

    @RequestMapping("/get_prepay_order.do")
    @ResponseBody
    public Msg getPrePayOrder(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        List<Order> orders = iOrderService.listPrePayOrder(userId);
        return Msg.createSucMsg(orders);
    }

    @RequestMapping("/get_all_order.do")
    @ResponseBody
    public Msg getAllOrder(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        List<Order> orders = iOrderService.listOrder(userId);
        return Msg.createSucMsg(orders);
    }
}
