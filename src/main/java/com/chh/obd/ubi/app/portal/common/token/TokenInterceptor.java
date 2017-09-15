package com.chh.obd.ubi.app.portal.common.token;

import com.alibaba.fastjson.JSON;
import com.chh.obd.ubi.app.portal.common.Constants;
import com.chh.obd.ubi.app.portal.common.response.RestResponse;
import com.chh.obd.ubi.app.portal.common.response.RestUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by niow on 2017/7/24.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    private String loginUrl;

    @Autowired
    private TokenFormat tokenFormat;


    /**
     * 从cookie获取token
     * 如果获取到token 并且没有过期  return true
     * 如果没有获取到  或者过期       返回登陆界面/json数据?
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return
     * @throws Exception
     */


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean isRestful = false;
        if (handlerMethod.getReturnType().equals(RestResponse.class)) {
            isRestful = true;
        }

        Cookie[] cookies = httpServletRequest.getCookies();

        Token token = null;
        String strToken = null;
        if (null != cookies) {
            try {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(Constants.COOKIE_TOKEN)) {
                        strToken = cookie.getValue().toString();
                        break;
                    }
                }
            } catch (Exception exp) {
                throw exp;
            }
            token = tokenFormat.parse(strToken);
        }

        if (null != token && token.getExpirationTime() > System.currentTimeMillis()) {
            return true;
        }

        if (isRestful){
            RestResponse rs = RestUtil.getResponse();
            response(isRestful, rs, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect(loginUrl);
        }

        return true;
    }

    /**
     * 回写json数据
     *
     * @param restResponse
     * @param response
     * @throws IOException
     */
    private void writeBackJson(RestResponse restResponse, HttpServletResponse response) throws IOException {
        String json = JSON.toJSONString(restResponse);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        IOUtils.write(json.getBytes(), response.getWriter());
    }

    private void response(boolean isRestful, RestResponse restResponse, HttpServletResponse response) {
        try {
            if (isRestful) {
                writeBackJson(restResponse, response);
            } else {
                    response.sendRedirect(loginUrl);
            }
        } catch (IOException e) {
            log.error("鉴权拦截器返回结果异常", e);
        }
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
