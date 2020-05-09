package com.bw.movie.bean;

import java.util.List;

public class FindUserFollowCinemaListBean {

    /**
     * result : [{"address":"建国门外大街1号国贸商城区域三地下一层3B120","cinemaId":7,"logo":"http://mobile.bwstudent.com/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"崇文门外大街18号国瑞城首层、地下二层","cinemaId":9,"logo":"http://mobile.bwstudent.com/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店"},{"address":"湖景东路11号新奥购物中心B1(东A口)","cinemaId":5,"logo":"http://mobile.bwstudent.com/images/movie/logo/CGVxx.jpg","name":"CGV星星影城"}]
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
         * address : 建国门外大街1号国贸商城区域三地下一层3B120
         * cinemaId : 7
         * logo : http://mobile.bwstudent.com/images/movie/logo/blg.jpg
         * name : 北京百丽宫影城
         */

        private String address;
        private int cinemaId;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
