package com.jk.yueba.bean;

/**
 * Created by jack on 16/5/26.
 */
public class User {

    private String account;
    private String pswd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }


}


