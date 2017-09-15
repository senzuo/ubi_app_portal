package com.chh.obd.ubi.app.portal.common.token;

/**
 * Created by niow on 2017/7/18.
 */
public class Token {

    /**
     * web登录换取的token
     */
    public static final short TYPE_WEB = 1;

    /**
     * ios登录换取的token
     */
    public static final short TYPE_IOS = 2;

    /**
     * android登录换取的token
     */
    public static final short TYPE_ANDROID = 3;

    /**
     * wechat登录换取的token
     */
    public static final short TYPE_WECHAT = 4;

    public Token() {

    }

    public Token(String id, Long userId, Long expirationTime,Short type) {
        this.id = id;
        this.userId = userId;
        this.expirationTime = expirationTime;
        this.type = type;
    }

    private String id;

    private Long userId;

    private Short type;

    private Long expirationTime;

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
