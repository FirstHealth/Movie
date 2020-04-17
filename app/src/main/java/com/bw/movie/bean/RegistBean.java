package com.bw.movie.bean;

/**
 * @ClassName RegistBean
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1714:42
 */
public class RegistBean {
    /**
     * message : 注册成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
