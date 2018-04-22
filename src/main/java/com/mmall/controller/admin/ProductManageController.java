package com.mmall.controller.admin;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/product/")
public class ProductManageController extends BaseController{

    @Autowired
    private IProductService iProductService;

    /**
     * 添加或更新商品
     * @param httpSession
     * @param product
     * @return
     */
    @RequestMapping("save_product.do")
    @ResponseBody
    public Msg saveProduct(HttpSession httpSession, Product product){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
             return iProductService.saveProduct(product);
        }
        return Msg.createFailMsg(Result.NOT_ALLOW);
    }

    /**
     * 更新商品的销售状态
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping("update_product_status.do")
    @ResponseBody
    public Msg updateProductStatus(HttpSession httpSession, Integer productId, Integer status){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            return iProductService.setSaleStatus(productId, status);
        }
        return Msg.createFailMsg(Result.NOT_ALLOW);
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public Msg getDetail(HttpSession httpSession, Integer productId){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            //填充业务
            return iProductService.manageProductDetail(productId);

        }else{
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public Msg getList(HttpSession httpSession, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            //填充业务
            return iProductService.listProducts(pageNo, pageSize);
        }else{
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

}
