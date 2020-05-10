package com.bw.movie.utils;

import com.bw.movie.bean.AreaBean1;
import com.bw.movie.bean.DataListBean;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.FindAllCinemaCommentBean;
import com.bw.movie.bean.FindCinemaInfoBean;
import com.bw.movie.bean.FindUserFollowCinemaListBean;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MovieCinecisnBean;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.MovieTingBean;
import com.bw.movie.bean.NearBean;
import com.bw.movie.bean.NearBean2;
import com.bw.movie.bean.PayBean;
import com.bw.movie.bean.QueryByKeyBean;
import com.bw.movie.bean.QueryCinemaBean;
import com.bw.movie.bean.QueryZuoWeiBean;
import com.bw.movie.bean.RecomeBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.bean.UploadHeadPicBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.ZFBBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName Apis
 * @Description TODO
 * @Author tys
 * @Date 2020/3/2418:15
 */
public interface Apis {
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<RegistBean> doRegist(@Field("nickName")String nickName,@Field("pwd")String pwd,@Field("email")String email,@Field("code")String code);

    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<EmailBean> doEmail(@Field("email")String email);

    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginBean> doLogin(@Field("email")String email,@Field("pwd")String pwd);

    @GET("movie/v2/findHotMovieList")
    Observable<HotMovie> doHot(@Query("page") int page,@Query("count") int count);

    @GET("movie/v2/findReleaseMovieList")
    Observable<ReleaseingMovie> doReleaseing(@Query("page") int page,@Query("count") int count);

    @GET("movie/v2/findComingSoonMovieList")
    Observable<UpcomingBean> doUpcoming(@Query("page") int page,@Query("count") int count);

    @GET("movie/v2/findMovieByKeyword")
    Observable<QueryByKeyBean> doQuery(@Query("keyword") String keyword,@Query("page") int page,@Query("count") int count);

    //查询电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<MovieDataBean> movieData(@Query("movieId")int movieId);

    @GET("movie/v1/verify/followMovie")
    Observable<RegistBean> doGuan(@Query("movieId")int movieId);

    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<RegistBean> doQuGuan(@Query("movieId") int movieId);

    //根据电影的id查询电影评论
    @GET("movie/v2/findAllMovieComment")
    Observable<MovieCinecisnBean> moviecomment(@Query("movieId")int movieId, @Query("page")int page, @Query("count")int count);

    @POST("movie/v2/verify/reserve")
    @FormUrlEncoded
    Observable<RegistBean> doYuYue(@Field("movieId")int movid);

    @POST("movie/v1/verify/movieComment")
    @FormUrlEncoded
    Observable<RegistBean> doWritePing(@Field("movieId")int movieId,@Field("commentContent")String commentContent,@Field("score")double score);

    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecomeBean> doRecome(@Query("page")int page,@Query("count")int count);

    @GET("cinema/v1/findNearbyCinemas")
    Observable<NearBean> doNear(@Query("longitude")String la,@Query("latitude")String laa,@Query("page")int page,@Query("count")int count);

    @GET("tool/v2/findRegionList")
    Observable<AreaBean1> doAreaOne();

    @GET("cinema/v2/findCinemaByRegion")
    Observable<NearBean2> doNear2(@Query("regionId")int regionId);

    @GET("movie/v2/findCinemasInfoByRegion")
    Observable<QueryCinemaBean> doQueryBean(@Query("movieId")int movieId, @Query("regionId")int regionId, @Query("page")int page, @Query("count")int count);

    @GET("movie/v2/findSeatInfo")
    Observable<QueryZuoWeiBean> doZuoWei(@Query("hallId")int hallId);

    @GET("movie/v2/findMovieSchedule")
    Observable<MovieTingBean> doTing(@Query("movieId")int movieId,@Query("cinemaId")int cinemaId);

    @POST("movie/v2/verify/buyMovieTickets")
    @FormUrlEncoded
    Observable<XiaDanBean> doXiaDan(@Field("scheduleId")int scheduleId,@Field("seat")String seat,@Field("sign")String sign);

    @POST("movie/v2/verify/pay")
    @FormUrlEncoded
    Observable<PayBean> doPay(@Field("payType")int payType,@Field("orderId")String orderId);

    @POST("movie/v2/verify/pay")
    @FormUrlEncoded
    Observable<ZFBBean> doPayZFB(@Field("payType")int payType, @Field("orderId")String orderId);

    //头像上传
    @POST("user/v1/verify/uploadHeadPic")
    Observable<UploadHeadPicBean> getUpLoadHeadPicBean(@Header("userId") int userId, @Header("sessionId")String sessionId, @Body RequestBody body);

    //查询用户关注影院列表
    @GET("user/v2/verify/findUserFollowCinemaList")
    Observable<FindUserFollowCinemaListBean>  findUserFollowCinemaList(@Header("userId") int userId, @Header("sessionId")String sessionId,
                                                                       @Query("page")int page, @Query("count")int count);

    //查询用户关注电影列表
    @GET("user/v2/verify/findUserFollowMovieList")
    Observable<UserFollowMovieBean> findUserFollowMovieList(@Header("userId") int userId, @Header("sessionId")String sessionId,
                                                            @Query("page")int page,
                                                            @Query("count")int count);

    //查询电影信息明细
    @GET("cinema/v1/findCinemaInfo")
    Observable<FindCinemaInfoBean> findCinemaInfo(@Query("cinemaId")int cinemaId);

    //查询影院用户评论列表
    @GET("cinema/v1/findAllCinemaComment")
    Observable<FindAllCinemaCommentBean> findAllCinemaComment(@Query("cinemaId")int cinemaId,
                                                              @Query("page")int page, @Query("count")int count);

    //影院评论点赞
    @POST("cinema/v1/verify/cinemaCommentGreat")
    @FormUrlEncoded
    Observable<RegistBean> cinemaCommentGreat(@Header("userId") int userId,@Header("sessionId")String sessionId,
                                                 @Field("commentId") int commentId);

    //关注影院
    @GET("cinema/v1/verify/followCinema")
    Observable<RegistBean> followCinema(@Header("userId") int userId,@Header("sessionId")String sessionId,
                                           @Query("cinemaId")int cinemaId);

    //查询影院下的电影排期
    @GET("cinema/v2/findCinemaScheduleList")
    Observable<ScheduleBean>  findCinemaScheduleList(@Query("cinemaId")int cinemaId, @Query("page") int page, @Query("count") int count);

    //查询一周排期的时间
    @GET("tool/v2/findDateList")
    Observable<DataListBean> datalist();
}
