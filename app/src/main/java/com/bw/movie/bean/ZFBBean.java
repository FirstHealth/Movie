package com.bw.movie.bean;

/**
 * @ClassName ZFBBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/916:46
 */
public class ZFBBean {
    String status;
    String message;
    String result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ZFBBean(String status, String message, String result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
