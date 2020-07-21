package com.example.bean;

import java.util.List;

public class UploadBean {

    /**
     * code : 500
     * res : 上传文件类型错误
     * err : 错误描述没有错误发生,文件上传成功
     * data : {"temp":[""],"filename":null,"filetype":null}
     */

    private int code;
    private String res;
    private String err;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * temp : [""]
         * filename : null
         * filetype : null
         */

        private Object filename;
        private Object filetype;
        private List<String> temp;

        public Object getFilename() {
            return filename;
        }

        public void setFilename(Object filename) {
            this.filename = filename;
        }

        public Object getFiletype() {
            return filetype;
        }

        public void setFiletype(Object filetype) {
            this.filetype = filetype;
        }

        public List<String> getTemp() {
            return temp;
        }

        public void setTemp(List<String> temp) {
            this.temp = temp;
        }
    }
}
