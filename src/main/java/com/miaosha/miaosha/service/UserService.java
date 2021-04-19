package com.miaosha.miaosha.service;

import com.miaosha.miaosha.dao.UserDao;
import com.miaosha.miaosha.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/18 21:31
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

}
