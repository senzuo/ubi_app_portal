package com.chh.obd.ubi.app.portal.user.controller;

import com.chh.obd.ubi.app.portal.common.response.RestResponse;
import com.chh.obd.ubi.app.portal.common.response.RestUtil;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 申卓 on 2017/9/15.
 */

@RestController
public class RestUserController {
    @RequestMapping(value="/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录", httpMethod = "GET", response = RestUserController.class, notes = "接收user对象登录")
    public RestResponse login(){
        RestResponse restResponse = RestUtil.getResponse();
        restResponse.setCode(200);
        restResponse.setData("token");
        return restResponse;
    }


}
