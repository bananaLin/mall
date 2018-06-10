package com.mmall.service;

import com.mmall.pojo.SecKillGoods;

public interface ISecKillGoodsService {

    boolean addOrUpdate(SecKillGoods secKillGoods);

    boolean delete(Integer id);
}
