package com.bw.movie.utils;

import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MovieCinecisnBean;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.QueryByKeyBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.UpcomingBean;

import io.reactivex.Observable;
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
}
