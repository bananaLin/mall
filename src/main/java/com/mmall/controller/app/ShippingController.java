package com.mmall.controller.app;

<<<<<<< HEAD
import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/shipping")
@Controller
public class ShippingController extends BaseController{

    @Autowired
    private IShippingService iShippingService;

    /**
     * 添加收货地址
     * @param httpSession
     * @param shipping
     * @return
     */
    @RequestMapping("/add.do")
    @ResponseBody
    public Msg add(HttpSession httpSession, Shipping shipping){
        Integer userId = this.getCurrentUserId(httpSession);
        boolean result = iShippingService.add(userId, shipping);
        if(result){
            Msg.createSucMsg(result);
        }
        return Msg.createSucMsg(Result.ADD_SHIPPING_FAIL);
    }

    /**
     * 更新收货地址
     * @param shipping
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public Msg update(Shipping shipping){
        if(shipping == null){
            return Msg.createSucMsg(Result.SHIPPING_COULD_BE_NULL);
        }
        if(shipping.getUserId() == null){
            return Msg.createSucMsg(Result.SHIPPING_USER_ID_COULD_BE_NULL);
        }
        boolean result = iShippingService.update(shipping);
        if(result){
            Msg.createSucMsg(result);
        }
        return Msg.createSucMsg(Result.UPDATE_SHIPPING_FAIL);
    }

    /**
     * 获取收货地址列表
     * @param httpSession
     * @return
     */
    @RequestMapping("/get_all.do")
    @ResponseBody
    public Msg getAll(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        List<Shipping> list = iShippingService.list(userId);
        return Msg.createSucMsg(list);
    }

    /**
     * 获取收货地址
     * @param httpSession
     * @param shippingId
     * @return
     */
    @RequestMapping("/get.do")
    @ResponseBody
    public Msg get(HttpSession httpSession, Integer shippingId){
        Integer userId = this.getCurrentUserId(httpSession);
        if(shippingId == null){
            return Msg.createSucMsg(Result.SHIPPING_USER_ID_COULD_BE_NULL);
        }
        Shipping shipping = iShippingService.get(userId, shippingId);
        return Msg.createSucMsg(shipping);
    }

    /**
     * 删除收货地址
     * @param httpSession
     * @param shippingId
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public Msg delete(HttpSession httpSession, Integer shippingId){
        Integer userId = this.getCurrentUserId(httpSession);
        if(shippingId == null){
            return Msg.createSucMsg(Result.SHIPPING_USER_ID_COULD_BE_NULL);
        }
        boolean result = iShippingService.delete(userId, shippingId);
        return Msg.createSucMsg(result);
    }

    /**
     * 清空收货地址
     * @param httpSession
     * @return
     */
    @RequestMapping("/delete_all.do")
    @ResponseBody
    public Msg deleteAll(HttpSession httpSession){
        Integer userId = this.getCurrentUserId(httpSession);
        boolean result = iShippingService.deleteAll(userId);
        return Msg.createSucMsg(result);
=======
@Controller
@RequestMapping("/app/shipping")
public class ShippingController {

    @Autowrite
    ShippingService shippingService;
  
    @RequestMapping("/add.do")
    public Msg add(HttpSession httpSession, Shipping shpping){
        if(shpping == null){
            return null;
        }
        boolean result = shippingService.add(shpping);
        return null;
>>>>>>> 41867618204e3cd6b2aa658c08bf3c6fd620760a
    }
}
