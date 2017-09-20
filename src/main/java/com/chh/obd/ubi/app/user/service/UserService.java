package com.chh.obd.ubi.app.user.service;

import com.chh.obd.ubi.app.user.model.User;

/**
 * Created by 申卓 on 2017/9/19.
 */
public interface UserService {
    //    User findByUsernameAndPassword(String phoneNum, String Password);

    int createUser(User user);

    int updateByPrimaryKeySelective(User user);

    User getByPhone(String phone);

}
