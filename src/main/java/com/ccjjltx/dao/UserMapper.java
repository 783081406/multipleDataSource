package com.ccjjltx.dao;

import com.ccjjltx.entity.User;
import com.ccjjltx.util.DataSource;

@DataSource(value="2")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    @DataSource
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}