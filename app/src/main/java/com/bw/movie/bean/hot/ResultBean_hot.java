package com.bw.movie.bean.hot;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ClassName ResultBean_hot
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2922:35
 */
@Entity
public class ResultBean_hot {
    private String director;
    private String horizontalImage;
    private String imageUrl;
    @Id
    private Long movieId;
    private String name;
    private double score;
    private String starring;


    @Generated(hash = 1660150917)
    public ResultBean_hot(String director, String horizontalImage, String imageUrl,
            Long movieId, String name, double score, String starring) {
        this.director = director;
        this.horizontalImage = horizontalImage;
        this.imageUrl = imageUrl;
        this.movieId = movieId;
        this.name = name;
        this.score = score;
        this.starring = starring;
    }

    @Generated(hash = 1140199747)
    public ResultBean_hot() {
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getHorizontalImage() {
        return horizontalImage;
    }

    public void setHorizontalImage(String horizontalImage) {
        this.horizontalImage = horizontalImage;
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
