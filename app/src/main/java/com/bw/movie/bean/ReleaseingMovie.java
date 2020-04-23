package com.bw.movie.bean;

import java.util.List;

/**
 * @ClassName ReleaseingMovie
 * @Description TODO
 * @Author tys
 * @Date 2020/4/190:18
 */
public class ReleaseingMovie {


    /**
     * result : [{"director":"刘阔","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/fyz/fyz1.jpg","movieId":12,"name":"风语咒","score":9.7,"starring":"路知行,阎萌萌,褚珺"},{"director":"乔·德特杜巴","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jcs/jcs1.jpg","movieId":11,"name":"巨齿鲨","score":9.6,"starring":"杰森·斯坦森,李冰冰,雷恩·威尔森"},{"director":"韩延","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/dwsj/dwsj1.jpg","movieId":10,"name":"动物世界","score":9.5,"starring":"李易峰,迈克尔·道格拉斯,周冬雨"},{"director":"是枝裕和","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/xtjz/xtjz1.jpg","movieId":9,"name":"小偷家族","score":9.4,"starring":"中川雅也,安藤樱,松冈茉优,"},{"director":"胡安·安东尼奥·巴亚纳","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/zljgy/zljgy1.jpg","movieId":8,"name":"侏罗纪世界2","score":9.3,"starring":"克里斯·帕拉特,布莱丝·达拉斯·霍华德,泰德·拉文"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * director : 刘阔
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/fyz/fyz1.jpg
         * movieId : 12
         * name : 风语咒
         * score : 9.7
         * starring : 路知行,阎萌萌,褚珺
         */

        private String director;
        private String imageUrl;
        private int movieId;
        private String name;
        private double score;
        private String starring;

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

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
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
}
