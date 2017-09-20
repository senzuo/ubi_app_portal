package com.chh.obd.ubi.app.user.service.impl;

import com.chh.obd.ubi.app.user.dao.UserMapper;
import com.chh.obd.ubi.app.user.model.User;
import com.chh.obd.ubi.app.user.service.UserService;
import com.chh.obd.ubi.app.util.encrypt.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 申卓 on 2017/9/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDAO;


    @Override
    public int createUser(User user) {
        user.setPassword(MD5Util.md5s(user.getPassword()));
        return userDAO.insert(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        String passwordMds = MD5Util.md5s(user.getPassword());
        user.setPassword(passwordMds);
        return userDAO.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getByPhone(String phone) {
        return userDAO.selectByPrimaryKey(phone);
    }

}
