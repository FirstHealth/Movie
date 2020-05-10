package com.bw.movie.bean;

import java.util.List;

public class ScheduleBean {

    /**
     * result : [{"director":"陈凯歌","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/whwdzg/whwdzg1.jpg","movieId":23,"name":"我和我的祖国","score":9.7,"starring":"黄渤,张译,杜江,葛优,刘昊然,吴京","trailerUrl":"http://mobile.bwstudent.com/video/movie/whwdzg/whwdzg1.mp4"},{"director":"闫非","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"name":"西虹市首富","score":8.6,"starring":"沈腾,宋芸烨,张晨光,艾伦,常远","trailerUrl":"http://mobile.bwstudent.com/video/movie/xhssf/xhssf1.ts"}]
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
         * director : 陈凯歌
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/whwdzg/whwdzg1.jpg
         * movieId : 23
         * name : 我和我的祖国
         * score : 9.7
         * starring : 黄渤,张译,杜江,葛优,刘昊然,吴京
         * trailerUrl : http://mobile.bwstudent.com/video/movie/whwdzg/whwdzg1.mp4
         */

        private String director;
        private String imageUrl;
        private int movieId;
        private String name;
        private double score;
        private String starring;
        private String trailerUrl;

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

        public String getTrailerUrl() {
            return trailerUrl;
        }

        public void setTrailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
        }
    }
}
