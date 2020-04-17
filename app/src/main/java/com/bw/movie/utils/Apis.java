package com.bw.movie.utils;

import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegistBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
