package com.mmall.controller.admin;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.pojo.Product;
import com.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/product/")
public class ProductManageController extends BaseController{

    @Autowired
    private ProductService productService;

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
             return productService.saveProduct(product);
        }
        return Msg.createFailMsg(Result.NOT_ALLOW);
    }

    /**
     * 更新商品的销售状态
     * @param productId
     * @param status
     * @return
     */
    public Msg updateProductStatus(HttpSession httpSession, Integer productId, Integer status){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            return productService.setSaleStatus(productId, status);
        }
        return Msg.createFailMsg(Result.NOT_ALLOW);
    }

}
