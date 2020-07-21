package com.example.bean;

public class RegisterBean {

    /**
     * code : 10002
     * ret : 验证码错误
     */

    private int code;
    private String ret;

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

    @Override
    public String toString() {
        return "RegisterBean{" +
                "code=" + code +
                ", ret='" + ret + '\'' +
                '}';
    }
}
