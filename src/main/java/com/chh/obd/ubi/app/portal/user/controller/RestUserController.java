package com.chh.obd.ubi.app.portal.user.controller;

import com.chh.obd.ubi.app.portal.common.response.RestResponse;
import com.chh.obd.ubi.app.portal.common.response.RestUtil;
import com.chh.obd.ubi.app.portal.user.model.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 申卓 on 2017/9/15.
 */

@Controller
public class RestUserController {
    @RequestMapping(value="/login", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "登录", httpMethod = "GET", response = RestUserController.class, notes = "接收user对象登录")
    public RestResponse login(){
        RestResponse restResponse = RestUtil.getResponse();
        restResponse.setCode(200);
        restResponse.setData("token");
        return restResponse;
    }


}
