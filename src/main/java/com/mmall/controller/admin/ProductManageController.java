package com.mmall.controller.admin;

import com.google.common.collect.Maps;
import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.pojo.Product;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/product")
public class ProductManageController extends BaseController{

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    /**
     * 添加或更新商品
     * @param httpSession
     * @param product
     * @return
     */
    @RequestMapping("/save_product.do")
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
    @RequestMapping("/update_product_status.do")
    @ResponseBody
    public Msg updateProductStatus(HttpSession httpSession, Integer productId, Integer status){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            return iProductService.setSaleStatus(productId, status);
        }
        return Msg.createFailMsg(Result.NOT_ALLOW);
    }

    @RequestMapping("/detail.do")
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

    @RequestMapping("/list.do")
    @ResponseBody
    public Msg getList(HttpSession httpSession, String productName, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            //填充业务
            return iProductService.listProducts(productName, pageNo, pageSize);
        }else{
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

    /**
     * 上传文件
     * @param multipartFile
     * @param httpServletRequest
     * @return
     */
    public Msg upload(MultipartFile multipartFile, HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        boolean isAdmin =  this.isAdmin(httpSession);
        if(isAdmin){
            String path = httpSession.getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(multipartFile, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return Msg.createSucMsg(fileMap);
        }else{
            return Msg.createSucMsg(Result.NOT_ALLOW);
        }
    }

}
