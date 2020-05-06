package com.bw.movie.bean;

import java.util.List;

/**
 * @ClassName QueryCinemaBean
 * @Description TODO
 * @Author tys
 * @Date 2020/5/614:36
 */
public class QueryCinemaBean {
    /**
     * result : [{"address":"广顺北大街16号望京华彩商业中心B1","cinemaId":10,"logo":"http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院","price":0},{"address":"中关村广场购物中心津乐汇三层（鼎好一期西侧）","cinemaId":12,"logo":"http://mobile.bwstudent.com/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店","price":0}]
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
         * address : 广顺北大街16号望京华彩商业中心B1
         * cinemaId : 10
         * logo : http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg
         * name : 华谊兄弟影院
         * price : 0
         */

        private String address;
        private int cinemaId;
        private String logo;
        private String name;
        private int price;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
