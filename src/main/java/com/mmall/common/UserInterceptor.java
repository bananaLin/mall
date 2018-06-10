package com.mmall.common;

import com.mmall.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor{

    private static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("[进入用户拦截器...]");
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String url = httpServletRequest.getServletPath();
        if(user == null){

            // 登录、注册操作不能拦截
            if(url.indexOf("login") != -1 || url.indexOf("register") != -1 || url.indexOf("/allow") != -1){
                return true;
            }
            httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }
        logger.info("[当前用户为] user:" + user.getUsername() + "身份为：" + user.getRole());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
