package com.mmall.dao;

import com.mmall.pojo.SecKill;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SecKillMapper {

    SecKill selectByTitle(@Param("title") String title);

    SecKill selectById(@Param("id") Integer id);

    List<SecKill> selectTodaySecKill();

    SecKill selectFirstSecKill();

    int insert(SecKill secKill);

    int update(SecKill secKill);
}
