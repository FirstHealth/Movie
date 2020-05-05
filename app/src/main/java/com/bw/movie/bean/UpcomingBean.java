package com.bw.movie.bean;

import com.bw.movie.bean.upcoming.ResultBean_upcoming;

import java.util.List;

/**
 * @ClassName UpcomingBean
 * @Description TODO
 * @Author tys
 * @Date 2020/4/190:20
 */
public class UpcomingBean {


    /**
     * result : [{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieId":16,"name":"碟中谍6：全面瓦解","releaseTime":1600704000000,"wantSeeNum":62,"whetherReserve":2},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jmyx/jmyx1.jpg","movieId":14,"name":"解码游戏","releaseTime":1599062400000,"wantSeeNum":45,"whetherReserve":2},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/aqgy/aqgy1.jpg","movieId":15,"name":"爱情公寓","releaseTime":1596988800000,"wantSeeNum":32,"whetherReserve":2},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"name":"西虹市首富","releaseTime":1595779200000,"wantSeeNum":19,"whetherReserve":2},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","releaseTime":1595174400000,"wantSeeNum":9,"whetherReserve":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean_upcoming> result;

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

    public List<ResultBean_upcoming> getResult() {
        return result;
    }

    public void setResult(List<ResultBean_upcoming> result) {
        this.result = result;
    }


}
