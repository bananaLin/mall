package com.mmall.dao;

import com.mmall.pojo.SecKillGoods;

public interface SecKillGoodsMapper {

    int addOrUpdate(SecKillGoods secKillGoods);

    int delete(Integer id);
}
