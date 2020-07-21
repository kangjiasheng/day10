package com.example.bean;

import java.util.List;

public class LoginBean {

    /**
     * code : 502
     * ret : 请输入用户名和密码
     * data : []
     */

    private int code;
    private String ret;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
