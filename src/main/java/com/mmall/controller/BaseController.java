package com.mmall.controller;

import com.mmall.common.Const;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

//抽取重复代码
public class BaseController {


    @Autowired
    private IUserService iUserService;

    /**
     * 判断当前用户是否是管理员
     * @param httpSession
     * @return
     */
    public boolean isAdmin(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return iUserService.checkAdminRole(user).isSuccess();
    }

    /**
     * 获取当前用户id
     * @param httpSession
     * @return
     */
    public Integer getCurrentUserId(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return user.getId();
    }
}
