package com.mmall.service.impl;

import com.mmall.dao.SecKillGoodsMapper;
import com.mmall.pojo.SecKillGoods;
import com.mmall.service.ISecKillGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecKillGoodsServiceImpl implements ISecKillGoodsService{

    private static Logger logger = LoggerFactory.getLogger(SecKillGoodsServiceImpl.class);

    @Autowired
    private SecKillGoodsMapper secKillGoodsMapper;


    @Override
    public boolean addOrUpdate(SecKillGoods secKillGoods) {
        if(secKillGoods == null){
            return false;
        }
        logger.info("【秒杀商品】secKillGoods：" + secKillGoods.toString());

        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return secKillGoodsMapper.delete(id) > 0;
    }
}
