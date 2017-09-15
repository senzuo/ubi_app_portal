package com.chh.obd.ubi.app.portal.common.token;

import com.chh.obd.ubi.support.utils.GZipUtils;
import com.chh.obd.ubi.support.utils.encrypt.MD5Util;
import com.chh.obd.ubi.support.utils.string.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class TokenFormat {

    private Map<Character, Character> charMap = new HashMap<Character, Character>();

    private String loginTimeFormat = "yyyyMMddHHmmss.SSS";

    public TokenFormat() {
        charMap.put('0', '1');
        charMap.put('1', 'e');
        charMap.put('2', 'd');
        charMap.put('3', 'f');
        charMap.put('4', '0');
        charMap.put('5', 'b');
        charMap.put('6', 'a');
        charMap.put('7', 'c');
        charMap.put('8', '9');
        charMap.put('9', '2');
        charMap.put('a', '7');
        charMap.put('b', '3');
        charMap.put('c', '5');
        charMap.put('d', '6');
        charMap.put('e', '4');
        charMap.put('f', '8');
    }

    private String encrypt(String info) {
        if (StringUtils.isEmpty(info)) {
            return info;
        }
        StringBuffer result = new StringBuffer();
        int len = info.length();
        for (int n = 0; n < len; n++) {
            char c = info.charAt(n);
            if (charMap.containsKey(c)) {
                result.append(charMap.get(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * token -> String
     * @param token
     * @return
     */
    public String format(Token token) {
        if (token == null) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        String[] paramList = new String[10];
        paramList[0] = token.getUserId().toString();
        paramList[1] = token.getId();
        paramList[2] = token.getExpirationTime().toString();
        paramList[3] = token.getType().toString();
        result.append(GZipUtils.zip(paramList));
        result.append(encrypt(MD5Util.getMd5Min(result.toString())));
        return result.toString();
    }

    /**
     * 解析  String -> Token
     * @param info
     * @return
     */
    public Token parse(String info) {
        if (info == null || info.length() < 32) {
            return null;
        }
        int len = info.length();
        String md5 = info.substring(len - 32);
        len -= 32;
        info = info.substring(0, len);
        if (md5.equals(encrypt(MD5Util.getMd5Min(info)))) {
            String[] infos = GZipUtils.unZip(info);
            if (infos != null && infos.length == 10) {
                String userId = infos[0];
                if (userId == null) {
                    return null;
                }
                String id = infos[1];
                if (id == null) {
                    return null;
                }
                String expirationTime = infos[2];
                if (expirationTime == null) {
                    return null;
                }
                String type = infos[3];
                if (type == null) {
                    return null;
                }
                Token token = new Token(id, Long.parseLong(userId), Long.parseLong(expirationTime), Short.parseShort(type));
                return token;
            }
        }
        return null;
    }

}
