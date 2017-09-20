package com.chh.obd.ubi.app.user.model;

public class User {
    private String phoneNum;

    private String password;

    private Byte typeOs;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getTypeOs() {
        return typeOs;
    }

    public void setTypeOs(Byte typeOs) {
        this.typeOs = typeOs;
    }
}