package com.bw.movie.bean;

import java.util.List;

public class DataListBean {
    /**
     * result : ["05-08","05-09","05-10","05-11","05-12","05-13","05-14"]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<String> result;

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
