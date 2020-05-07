package com.bw.movie.bean.upcoming;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ClassName ResultBean_hot
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2922:37
 */
@Entity
public class ResultBean_upcoming {

    private String imageUrl;
    @Id
    private Long movieId;
    private String name;
    private long releaseTime;
    private int wantSeeNum;
    private int whetherReserve;

    @Generated(hash = 2031394497)
    public ResultBean_upcoming(String imageUrl, Long movieId, String name,
            long releaseTime, int wantSeeNum, int whetherReserve) {
        this.imageUrl = imageUrl;
        this.movieId = movieId;
        this.name = name;
        this.releaseTime = releaseTime;
        this.wantSeeNum = wantSeeNum;
        this.whetherReserve = whetherReserve;
    }

    @Generated(hash = 586712565)
    public ResultBean_upcoming() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getWantSeeNum() {
        return wantSeeNum;
    }

    public void setWantSeeNum(int wantSeeNum) {
        this.wantSeeNum = wantSeeNum;
    }

    public int getWhetherReserve() {
        return whetherReserve;
    }

    public void setWhetherReserve(int whetherReserve) {
        this.whetherReserve = whetherReserve;
    }
}