package com.kingwag.tinyworld.view.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by MSI on 2016/12/22.
 */

public class MyUser extends BmobUser {
    private String userName;
    private String password;
    private String introduce;
    private String mobilePhoneNumber;

    @Override
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    @Override
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
