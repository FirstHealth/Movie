package com.bw.movie.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.bw.movie.R;
import com.bw.movie.activity.MovieListActivity;
import com.bw.movie.adapter.HotAdapter;
import com.bw.movie.adapter.ReseasingAdapter;
import com.bw.movie.adapter.UpCommingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.bean.hot.ResultBean_hot;
import com.bw.movie.bean.reselea.ResultBean_reselea;
import com.bw.movie.bean.upcoming.ResultBean_upcoming;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.ResultBean_hotDao;
import com.bw.movie.dao.ResultBean_reseleaDao;
import com.bw.movie.dao.ResultBean_upcomingDao;
import com.bw.movie.presenter.MoviePresenter;
import com.bw.movie.utils.NetUtils;
import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName MovieFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1823:02
 */
public class MovieFragment extends BaseFragment implements MovieContract.IView , LocationSource, AMapLocationListener {
    @BindView(R.id.xb)
    XBanner xb;
    @BindView(R.id.rv_hot)
    RecyclerView rv;
    @BindView(R.id.rv_reseasing)
    RecyclerView rv2;
    @BindView(R.id.rv_upcomming)
    RecyclerView rv3;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.up)
    TextView up;
    @BindView(R.id.resa)
    TextView resa;
    @BindView(R.id.hot)
    TextView hot;
    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private LocationSource.OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    ArrayList<Integer> list = new ArrayList<>();
    private String city1;
    private ResultBean_hotDao resultBean_hotDao;
    private ResultBean_reseleaDao resultBean_reseleaDao;
    private ResultBean_upcomingDao resultBean_upcomingDao;


    @Override
    protected int getReasuce() {
        return R.layout.fragment_movie;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MoviePresenter(this);
    }

    @Override
    protected void getid(View view) {
        mapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();
    }

    @Override
    protected void getData() {

        DaoSession daoSession_hot = DaoMaster.newDevSession(getContext(), "hot");
        resultBean_hotDao = daoSession_hot.getResultBean_hotDao();
        DaoSession daoSession_reseles = DaoMaster.newDevSession(getContext(), "reseles");
        resultBean_reseleaDao = daoSession_reseles.getResultBean_reseleaDao();
        DaoSession daoSession_upcoming = DaoMaster.newDevSession(getContext(), "upcoming");
        resultBean_upcomingDao = daoSession_upcoming.getResultBean_upcomingDao();

        if (city1 != null){
            city.setText(city1+"");
        }
        list.add(R.mipmap.banner_1);
        list.add(R.mipmap.banner_2);
        list.add(R.mipmap.banner_3);
        list.add(R.mipmap.banner_4);

        xb.setData(list,null);
        xb.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView iv = (ImageView) view;
                iv.setImageResource(list.get(position));
            }
        });

        if (NetUtils.getInstance().isNetWork(getContext())){
            BasePresenter presenter = getPresenter();
            if (presenter != null && presenter instanceof MoviePresenter){
                ((MoviePresenter) presenter).getHotData(1,5);
                ((MoviePresenter) presenter).getReleaseingData(1,5);
                ((MoviePresenter) presenter).getUpcommingData(1,5);
            }
        }else {
            Toast.makeText(getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
            List<ResultBean_hot> list1 = resultBean_hotDao.queryBuilder().list();
            HotMovie hotMovie = new HotMovie();
            hotMovie.setResult(list1);
            onHotSuccess(hotMovie);

            List<ResultBean_reselea> list2 = resultBean_reseleaDao.queryBuilder().list();
            ReleaseingMovie releaseingMovie = new ReleaseingMovie();
            releaseingMovie.setResult(list2);
            onReleaseingSuccess(releaseingMovie);

            List<ResultBean_upcoming> list3 = resultBean_upcomingDao.queryBuilder().list();
            UpcomingBean upcomingBean = new UpcomingBean();
            upcomingBean.setResult(list3);
            onUpcommingSuccess(upcomingBean);

        }

    }

    @Override
    public void onHotSuccess(HotMovie bean) {
        List<ResultBean_hot> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        HotAdapter hotAdapter = new HotAdapter(getContext(), result);
        rv.setAdapter(hotAdapter);
        rv.addItemDecoration(new SpaceItemDecoration(10));

        for (int i = 0; i< result.size(); i++){
            resultBean_hotDao.insertOrReplace(result.get(i));
        }

    }

    @Override
    public void onHotError(String str) {

    }

    @Override
    public void onReleaseingSuccess(ReleaseingMovie bean) {
        List<ResultBean_reselea> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(manager);
        ReseasingAdapter reseasingAdapter = new ReseasingAdapter(getContext(), result);
        rv2.setAdapter(reseasingAdapter);
        rv2.addItemDecoration(new SpaceItemDecoration(10));

        for (int i = 0; i < result.size(); i++){
            resultBean_reseleaDao.insertOrReplace(result.get(i));
        }

    }

    @Override
    public void onReleaseingError(String str) {

    }

    @Override
    public void onUpcommingSuccess(UpcomingBean bean) {
        List<ResultBean_upcoming> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv3.setLayoutManager(manager);
        UpCommingAdapter commingAdapter = new UpCommingAdapter(getContext(), result);
        rv3.setAdapter(commingAdapter);
        rv3.addItemDecoration(new SpaceItemDecoration(20));

        for (int i = 0; i < result.size(); i++){
            resultBean_upcomingDao.insertOrReplace(result.get(i));
        }

    }

    @Override
    public void onUpcommingError(String str) {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    //Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    city1 = aMapLocation.getCity();
                    //Toast.makeText(getContext(), ""+city1+"11", Toast.LENGTH_SHORT).show();
                    city.setText(city1+"");
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                //Toast.makeText(getContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @OnClick(R.id.search)
    public void onClick(View view){
        Intent intent = new Intent(getActivity(), MovieListActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.up,R.id.resa,R.id.hot})
    public void onc(View v){
        switch (v.getId()){
            case R.id.up:
                Intent intent = new Intent(getActivity(), MovieListActivity.class);
                intent.putExtra("x","0");
                startActivity(intent);
                break;
            case R.id.resa:
                Intent intentt = new Intent(getActivity(), MovieListActivity.class);
                intentt.putExtra("x","1");
                startActivity(intentt);
                break;
            case R.id.hot:
                Intent intenttt = new Intent(getActivity(), MovieListActivity.class);
                intenttt.putExtra("x","2");
                startActivity(intenttt);
                break;
        }
    }
}
