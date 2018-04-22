package com.mmall.controller;

import com.mmall.common.Const;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseController {


    @Autowired
    private IUserService iUserService;

    /**
     * 抽取重复代码
     * @param httpSession
     * @return
     */
    public boolean isAdmin(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return iUserService.checkAdminRole(user).isSuccess();
    }
}
