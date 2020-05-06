package com.bw.movie.bean;

public class SiteBean {
    public static final int SITE_CAN_SELECT = 1;
    public static final int SITE_HAS_SAILED = 2;
    public static final int SITE_IS_SELECTED = 3;

    private int row;
    private int seat;
    private int status;

    public SiteBean(int row, int seat, int status) {
        this.row = row;
        this.seat = seat;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
