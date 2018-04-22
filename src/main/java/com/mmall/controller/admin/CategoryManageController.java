package com.mmall.controller.admin;

import com.mmall.common.Msg;
import com.mmall.common.Result;
import com.mmall.controller.BaseController;
import com.mmall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category/")
public class CategoryManageController extends BaseController{

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public Msg addCategory(HttpSession httpSession, String categoryName, @RequestParam(value="parentId", defaultValue = "0") int parentId){
        boolean isAdmin = this.isAdmin(httpSession);
        if(isAdmin){
            return iCategoryService.addCategory(categoryName, parentId);
        }else{
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public Msg setCategoryName(HttpSession httpSession, Integer categoryId, String categoryName) {
        boolean isAdmin = this.isAdmin(httpSession);
        if (isAdmin) {
            //更新categoryName
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public Msg getChildrenParallelCategory(HttpSession httpSession, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        boolean isAdmin = this.isAdmin(httpSession);
        if (isAdmin) {
            //查询子节点的category信息,并且不递归,保持平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        } else {
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public Msg getCategoryAndDeepChildrenCategory(HttpSession httpSession, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        boolean isAdmin = this.isAdmin(httpSession);
        if (isAdmin) {
            //查询当前节点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        } else {
            return Msg.createFailMsg(Result.NOT_ALLOW);
        }
    }

}
