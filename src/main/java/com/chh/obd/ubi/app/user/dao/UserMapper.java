package com.chh.obd.ubi.app.user.dao;

import com.chh.obd.ubi.app.user.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String phoneNum);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String phoneNum);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}