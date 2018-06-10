package com.mmall.service.impl;

import com.mmall.dao.SecKillMapper;
import com.mmall.pojo.SecKill;
import com.mmall.service.ISecKillService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class SecKillServiceImpl implements ISecKillService{

    private static Logger logger = LoggerFactory.getLogger(SecKillServiceImpl.class);

    @Autowired
    private SecKillMapper secKillMapper;

    @Override
    public boolean addOrUpdateSecKill(SecKill secKill) {

        if(secKill == null){
            return false;
        }
        logger.info("【秒杀活动】secKill：" + secKill.toString());
        int resultCount = 0;
        if(secKill.getId() != null && secKill.getId() > 0){
            resultCount = secKillMapper.update(secKill);
        }else {
            if(StringUtils.isBlank(secKill.getTitle())){
                return false;
            }
            resultCount = secKillMapper.insert(secKill);
        }
        logger.info("【秒杀活动】 resultCount："+resultCount);
        return true;
    }

    @Override
    public List<SecKill> listTodaySecKill() {
        return secKillMapper.selectTodaySecKill();
    }

    @Override
    public boolean isCurrentSecKill(Integer id) {
        SecKill secKill = secKillMapper.selectById(id);
        if(secKill == null){
            return false;
        }
        Date now = new Date();
        return now.after(secKill.getStartTime()) && now.before(secKill.getEndTime());
    }

    @Override
    public SecKill getFirstSecKill() {
        return secKillMapper.selectFirstSecKill();
    }
}
