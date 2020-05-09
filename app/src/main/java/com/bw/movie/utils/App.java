package com.bw.movie.utils;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @ClassName App
 * @Description TODO
 * @Author tys
 * @Date 2020/3/614:46
 */
public class App extends Application {
    private static Context context;
    private static IWXAPI msgApi;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fresco.initialize(this);
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wxb3852e6a6b7d9516");
    }

    public static Context getContext(){
        return context;
    }

    public static IWXAPI getIWXAPI(){
        return msgApi;
    }

}
