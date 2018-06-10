package com.mmall.controller.admin;

import com.mmall.common.Msg;
import com.mmall.pojo.SecKillGoods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/seckillGoods")
@Controller
public class SecKillGoodsController {

    /**
     * 添加或更新秒杀商品
     * @param secKillGoods
     * @return
     */
    @RequestMapping("/addGoodsOrUpdate.do")
    @ResponseBody
    public Msg addOrUpdateSecKillGoods(SecKillGoods secKillGoods){
        return null;
    }

    /**
     * 删除秒杀商品
     * @param id
     * @return
     */
    @RequestMapping("/deleteGoods.do")
    @ResponseBody
    public Msg deleteSecKillGoods(Integer id){
        return null;
    }
}
