package com.chh.obd.ubi.app.user.controller;

import com.chh.obd.ubi.app.common.response.RestCode;
import com.chh.obd.ubi.app.common.response.RestUtil;
import com.chh.obd.ubi.app.common.response.RestResponse;
import com.chh.obd.ubi.app.user.model.User;
import com.chh.obd.ubi.app.user.model.UserDTO;
import com.chh.obd.ubi.app.user.service.UserService;
import com.chh.obd.ubi.app.util.encrypt.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 申卓 on 2017/9/15.
 */

@RestController
public class RestUserLoginController {

    @Autowired
    private UserService userService;


    /**
     * 登录
     * @param userDTO
     * @return
     */
    @RequestMapping(value="/login")
    public RestResponse login(UserDTO userDTO){

        // 校验短信验证码

        // 校验图片验证码



        // 校验用户名密码
        User user = userService.getByPhone(userDTO.getPhoneNum());
        if (user == null){
            return RestUtil.getResponse(RestCode.TARGET_IS_NULL);
        }

        String password = MD5Util.md5s(user.getPassword());
        if (user == null || !password.equals(userDTO.getPassword())) {
            return RestUtil.getResponse(RestCode.LOGIN_INVALID);
        }

        //token session


        RestResponse response = RestUtil.getResponse();
        response.setData(user);
        return response;
    }

    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    @RequestMapping("/create")
    public RestResponse addUser(UserDTO userDTO) {

        // 校验短信验证码

        // 校验图片验证码

        User user = new User();
        user.setPhoneNum(userDTO.getPhoneNum());
        user.setPassword(userDTO.getPassword());
        user.setTypeOs((byte) 1);


        RestResponse response = RestUtil.getResponse();
        try {
            int id = userService.createUser(user);
        } catch (Exception e) {
            response.setRestCode(RestCode.TARGET_EXISTED);
        }
        return response;
    }

    /**
     * 找回密码
     * @param userDTO
     * @return
     */
    @RequestMapping("/update")
    public RestResponse updatePassword(UserDTO userDTO) {

        // 校验短信验证码

        // 校验图片验证码

        User user = new User();
        user.setPhoneNum(userDTO.getPhoneNum());
        user.setPassword(userDTO.getPassword());


        RestResponse response = RestUtil.getResponse();
        try {
            int id = userService.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            response.setRestCode(RestCode.ERROR);
        }
        return response;
    }


}
