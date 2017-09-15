package com.chh.obd.ubi.app.portal.common.token;

import com.chh.obd.ubi.support.common.cache.CacheFactory;
import com.chh.obd.ubi.support.common.cache.MapCache;

/**
 * Created by niow on 2017/7/21.
 */
public class TokenManager {

    private int cacheType = MapCache.TYPE_LOCAL_MAP;

    private MapCache<String, Token> tokenMapCache;

    /**
     * token有效时间分钟数，默认60分钟
     */
    private int tokenTimeOutMins = 60;

    public TokenManager() {
    }

    public TokenManager(int cacheType) {
        this.cacheType = cacheType;
    }

    public void init() {
       tokenMapCache = CacheFactory.createCache(this.cacheType);
    }


    public int getCacheType() {
        return cacheType;
    }

    public void setCacheType(int cacheType) {
        this.cacheType = cacheType;
    }

    public int getTokenTimeOutMins() {
        return tokenTimeOutMins;
    }

    public void setTokenTimeOutMins(int tokenTimeOutMins) {
        this.tokenTimeOutMins = tokenTimeOutMins;
    }

    public Token createToken(Long userId, short type) {
        Token token = new Token();
        token.setUserId(userId);
        token.setType(type);
        token.setExpirationTime(System.currentTimeMillis()+tokenTimeOutMins*6000);
        return token;
    }
}
