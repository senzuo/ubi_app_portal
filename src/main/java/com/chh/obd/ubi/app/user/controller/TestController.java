package com.chh.obd.ubi.app.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 申卓 on 2017/9/20.
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String foo(){
        return "index";
    }
}
