package com.bw.movie.bean.reselea;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ClassName ResultBean_hot
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2922:36
 */
@Entity
public class ResultBean_reselea {

    private String director;
    private String imageUrl;
    @Id
    private Long movieId;
    private String name;
    private double score;
    private String starring;

    @Generated(hash = 567850977)
    public ResultBean_reselea(String director, String imageUrl, Long movieId,
            String name, double score, String starring) {
        this.director = director;
        this.imageUrl = imageUrl;
        this.movieId = movieId;
        this.name = name;
        this.score = score;
        this.starring = starring;
    }

    @Generated(hash = 1341633083)
    public ResultBean_reselea() {
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }
}