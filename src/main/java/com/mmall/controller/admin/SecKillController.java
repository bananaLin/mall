package com.mmall.controller.admin;

import com.mmall.common.Msg;
import com.mmall.controller.BaseController;
import com.mmall.pojo.SecKill;
import com.mmall.service.ISecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/seckill")
@Controller
public class SecKillController extends BaseController{

    @Autowired
    private ISecKillService iSecKillService;

    @RequestMapping("/addOrUpdateSecKill")
    @ResponseBody
    public Msg addOrUpdateSecKill(HttpSession httpSession, SecKill secKill){
        Integer userId = this.getCurrentUserId(httpSession);
        boolean result = iSecKillService.addOrUpdateSecKill(secKill);
        return Msg.createSucMsg(result);
    }
}
