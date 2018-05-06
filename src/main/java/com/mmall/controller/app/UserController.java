package com.mmall.controller.app;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.controller.BaseController;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录
     * @param username
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession httpSession){

        ServerResponse serverResponse = iUserService.login(username, password);
        if(serverResponse.isSuccess()){
            httpSession.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }
        return serverResponse;
    }

    /**
     * 退出登录
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/logout.do")
    @ResponseBody
    public ServerResponse<String> logout(HttpSession httpSession){
        httpSession.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register.do")
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    /**
     * 校验
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "/check_valid.do")
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str, type);
    }

    /**
     * 获取当前用户信息
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/get_user_info.do")
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录");
    }

    /**
     * 忘记密码
     * @param username
     * @return
     */
    @RequestMapping(value = "/forget_get_question.do")
    @ResponseBody
    public ServerResponse forgetGetQuestion(String username){
        return iUserService.selectForgetQuestion(username);
    }

    /**
     * 校验问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "/forget_check_answer.do")
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer){
        return iUserService.checkAnswer(username, question, answer);
    }

    /**
     * 重置密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "/forget_rest_password.do")
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken){
        return iUserService.forgetRestPassword(username, passwordNew, forgetToken);
    }

    /**
     * 登录的状态下修改密码
     * @param httpSession
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "/rest_password.do")
    @ResponseBody
    public ServerResponse<String> restPassword(HttpSession httpSession, String passwordOld, String passwordNew){
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return iUserService.restPassword(user, passwordOld, passwordNew);
    }

    /**
     * 更新用户信息
     * @param httpSession
     * @param user
     * @return
     */
    @RequestMapping(value = "/update_information.do")
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession httpSession, User user){
        User currentUser = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            httpSession.setAttribute(Const.CURRENT_USER, user);
        }
        return response;
    }

    /**
     * 获取用户信息
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/get_information.do")
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession httpSession){
        User currentUser = (User) httpSession.getAttribute(Const.CURRENT_USER);
        return iUserService.getInformation(currentUser.getId());
    }



}
