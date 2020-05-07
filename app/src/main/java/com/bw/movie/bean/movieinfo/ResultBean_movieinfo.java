package com.bw.movie.bean.movieinfo;

import com.bw.movie.convert.ListToStringConvert_MovieActorBean;
import com.bw.movie.convert.ListToStringConvert_MovieDirectorBean;
import com.bw.movie.convert.ListToStringConvert_ShortFilmListBean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ClassName ResultBean_movieinfo
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2923:26
 */
@Entity
public class ResultBean_movieinfo {
    private int commentNum;
    private String duration;
    private String imageUrl;
    @Id
    private Long movieId;
    private String movieType;
    private String name;
    private String placeOrigin;
    private long releaseTime;
    private double score;
    private String summary;
    private int whetherFollow;
    @Convert(converter = ListToStringConvert_MovieActorBean.class, columnType = String.class )
    private List<MovieActorBean> movieActor;
    @Convert(converter = ListToStringConvert_MovieDirectorBean.class, columnType = String.class )
    private List<MovieDirectorBean> movieDirector;
    @Transient
    private List<String> posterList;
    @Convert(converter = ListToStringConvert_ShortFilmListBean.class, columnType = String.class )
    private List<ShortFilmListBean> shortFilmList;
    @Generated(hash = 268119466)
    public ResultBean_movieinfo(int commentNum, String duration, String imageUrl, Long movieId,
            String movieType, String name, String placeOrigin, long releaseTime, double score,
            String summary, int whetherFollow, List<MovieActorBean> movieActor,
            List<MovieDirectorBean> movieDirector, List<ShortFilmListBean> shortFilmList) {
        this.commentNum = commentNum;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.movieId = movieId;
        this.movieType = movieType;
        this.name = name;
        this.placeOrigin = placeOrigin;
        this.releaseTime = releaseTime;
        this.score = score;
        this.summary = summary;
        this.whetherFollow = whetherFollow;
        this.movieActor = movieActor;
        this.movieDirector = movieDirector;
        this.shortFilmList = shortFilmList;
    }

    @Generated(hash = 175428083)
    public ResultBean_movieinfo() {
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getWhetherFollow() {
        return whetherFollow;
    }

    public void setWhetherFollow(int whetherFollow) {
        this.whetherFollow = whetherFollow;
    }

    public List<MovieActorBean> getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(List<MovieActorBean> movieActor) {
        this.movieActor = movieActor;
    }

    public List<MovieDirectorBean> getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(List<MovieDirectorBean> movieDirector) {
        this.movieDirector = movieDirector;
    }

    public List<String> getPosterList() {
        return posterList;
    }

    public void setPosterList(List<String> posterList) {
        this.posterList = posterList;
    }

    public List<ShortFilmListBean> getShortFilmList() {
        return shortFilmList;
    }

    public void setShortFilmList(List<ShortFilmListBean> shortFilmList) {
        this.shortFilmList = shortFilmList;
    }
}
