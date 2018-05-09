package com.ccjjltx.service;

import com.ccjjltx.dao.UserMapper;
import com.ccjjltx.entity.User;
import com.ccjjltx.util.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void test1() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getUsername());
    }

    public void test2() {
        User user = new User();
        user.setId(100);
        user.setUsername("ccjccjccj");
        user.setPassword("cccccccccc");
        user.setUtype(1);
        userMapper.insert(user);
    }

}
