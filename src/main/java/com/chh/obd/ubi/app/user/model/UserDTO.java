package com.chh.obd.ubi.app.user.model;

/**
 * Created by 申卓 on 2017/9/19.
 */
public class UserDTO {
    private String phoneNum;
    private String password;
    private Byte type;
    private String messageCheck;
    private String imageCheck;

    public String getPhoneNum() {
        return phoneNum;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessageCheck() {
        return messageCheck;
    }

    public void setMessageCheck(String messageCheck) {
        this.messageCheck = messageCheck;
    }

    public String getImageCheck() {
        return imageCheck;
    }

    public void setImageCheck(String imageCheck) {
        this.imageCheck = imageCheck;
    }
}
