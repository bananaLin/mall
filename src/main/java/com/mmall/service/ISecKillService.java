package com.mmall.service;

import com.mmall.pojo.SecKill;
import java.util.List;

public interface ISecKillService {

    boolean addOrUpdateSecKill(SecKill secKill);

    List<SecKill> listTodaySecKill();

    boolean isCurrentSecKill(Integer id);

    SecKill getFirstSecKill();


}
