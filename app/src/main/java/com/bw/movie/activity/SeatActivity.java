package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.MovieTingAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieTingBean;
import com.bw.movie.bean.PayBean;
import com.bw.movie.bean.QueryZuoWeiBean;
import com.bw.movie.bean.SiteBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.custom.SeatTable;
import com.bw.movie.custom.SelectSiteView;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.MD5;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SeatActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.mSearchView)
    SeatTable mSearchView;
    @BindView(R.id.count)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.bu)
    Button bu;
    String orderId;
    private String id;
    ArrayList<SiteBean> list = new ArrayList<>();
    private String ima;
    private String movieid;
    private int hallId;
    private String screeningHall;
    private double fare;
    String a = "";
    int row1 = 0;
    int column1 = 0;

    @Override
    protected int getReasuce() {
        return R.layout.activity_seat;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {
//        if (!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }
//        Intent intent = getIntent();
//        id = intent.getStringExtra("id");
//        ima = intent.getStringExtra("ima");
//        movieid = intent.getStringExtra("movieid");
//        Glide.with(this).load(ima).into(iv);
    }

    @Override
    protected void getData() {
//        NetUtils.getInstance().getApis().doTing(Integer.valueOf(movieid),Integer.valueOf(id))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MovieTingBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(MovieTingBean bean) {
//                        List<MovieTingBean.ResultBean> result = bean.getResult();
//                        tv.setText("选择影厅和时间：( "+result.size()+" )");
//                        LinearLayoutManager manager = new LinearLayoutManager(SeatActivity.this, LinearLayoutManager.HORIZONTAL, false);
//                        rv.setLayoutManager(manager);
//                        MovieTingAdapter adapter = new MovieTingAdapter(SeatActivity.this, result);
//                        rv.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });






    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getD(MovieTingBean.ResultBean bean){
//        hallId = bean.getHallId();
//        fare = bean.getFare();
//        screeningHall = bean.getScreeningHall();
//        NetUtils.getInstance().getApis().doZuoWei(Integer.valueOf(hallId))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<QueryZuoWeiBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(QueryZuoWeiBean bean) {
//                        int maxh = 0;
//                        int maxl = 0;
//                        List<QueryZuoWeiBean.ResultBean> result = bean.getResult();
//                        for (int i = 0; i < result.size(); i++){
//                            if (maxh < Integer.valueOf(result.get(i).getRow())){
//                                maxh = Integer.valueOf(result.get(i).getRow());
//                            }
//                            if (maxl < Integer.valueOf(result.get(i).getSeat())){
//                                maxl = Integer.valueOf(result.get(i).getSeat());
//                            }
//                        }
//                        //sele.setData(Integer.valueOf(result.get(i).getRow()),Integer.valueOf(result.get(i).getSeat()));
//                        mSearchView.setScreenName(screeningHall);//设置屏幕名称
//                        mSearchView.setMaxSelected(3);//设置最多选中
//                        mSearchView.setSeatChecker(new SeatTable.SeatChecker() {
//                            @Override
//                            public boolean isValidSeat(int row, int column) {
//                                if(column==2) {
//                                    return false;
//                                }
//                                return true;
//                            }
//                            @Override
//                            public boolean isSold(int row, int column) {
//                                if(row==4&&column==6){
//                                    return true;
//                                }else if(row==6&&column==6){
//                                    return true;
//                                }
//                                return false;
//                            }
//                            @Override
//                            public void checked(int row, int column) {
//                                row1 = row;
//                                column1 = column;
//                                a=row+"-"+column;
//                                fare+=fare;
//                                bu.setText("￥"+fare);
//                                Toast.makeText(SeatActivity.this, "排"+row+"列"+column, Toast.LENGTH_SHORT).show();
//                            }
//                            @Override
//                            public void unCheck(int row, int column) {
//
//                            }
//                            @Override
//                            public String[] checkedSeatTxt(int row, int column) {
//                                return null;
//                            }
//                        });
//                        mSearchView.setData(maxh,maxl);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @OnClick(R.id.bu)
    public void onClick(View view){
//        String substring = a.substring(0, a.length() - 1);
//        String userid = SPUtil.getString(this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
//        String sessionid = SPUtil.getString(this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
//        String a = userid+"1"+"movie";
//        String jia = MD5.jia(a);
//        Toast.makeText(this, row1+"-"+column1, Toast.LENGTH_SHORT).show();
//        NetUtils.getInstance().getApis().doXiaDan(1,row1+"-"+column1,jia)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<XiaDanBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(XiaDanBean xiaDanBean) {
//                        orderId = xiaDanBean.getOrderId();
//                        NetUtils.getInstance().getApis().doPay(1,orderId)
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Observer<PayBean>() {
//                                    @Override
//                                    public void onSubscribe(Disposable d) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(PayBean payBean) {
//                                            IWXAPI api = null;
//                                            PayReq request = new PayReq();
//                                            request.appId = payBean.getAppId();
//                                            request.partnerId = payBean.getPartnerId();
//                                            request.prepayId= payBean.getPrepayId();
//                                            request.packageValue = payBean.getPackageValue();
//                                            request.nonceStr= payBean.getNonceStr();
//                                            request.timeStamp= payBean.getTimeStamp();
//                                            request.sign= payBean.getSign();
//                                            api.sendReq(request);
//                                        Toast.makeText(SeatActivity.this, ""+payBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }


}
