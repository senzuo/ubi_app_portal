package com.chh.obd.ubi.app.portal.common.token;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by niow on 2017/7/18.
 */
public class TokenUtil {

    public static Token getUser(HttpServletRequest httpServletRequest) {
        Token token = new Token();
        token.setUserId(1L);
        return token;
    }
}
