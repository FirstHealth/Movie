package com.bw.movie;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bw.movie.adapter.MovieTingAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieTingBean;
import com.bw.movie.bean.PayBean;
import com.bw.movie.bean.QueryZuoWeiBean;
import com.bw.movie.bean.SiteBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.ZFBBean;
import com.bw.movie.custom.SeatTable;
import com.bw.movie.utils.MD5;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.buu)
    Button zhi;
    String orderId;
    private String id;
    int SDK_PAY_FLAG = 0;
    ArrayList<SiteBean> list = new ArrayList<>();
    private String ima;
    private String movieid;
    private int hallId;
    private String screeningHall;
    private double fare;
    String a ="";
    IWXAPI api = null;
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
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        ima = intent.getStringExtra("ima");
        movieid = intent.getStringExtra("movieid");
        Glide.with(this).load(ima).into(iv);
    }

    @Override
    protected void getData() {
        NetUtils.getInstance().getApis().doTing(Integer.valueOf(movieid),Integer.valueOf(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieTingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieTingBean bean) {
                        List<MovieTingBean.ResultBean> result = bean.getResult();
                        tv.setText("选择影厅和时间：( "+result.size()+" )");
                        LinearLayoutManager manager = new LinearLayoutManager(SeatActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv.setLayoutManager(manager);
                        MovieTingAdapter adapter = new MovieTingAdapter(SeatActivity.this, result);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });






    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getD(MovieTingBean.ResultBean bean){
        hallId = bean.getHallId();
        fare = bean.getFare();
        screeningHall = bean.getScreeningHall();
        NetUtils.getInstance().getApis().doZuoWei(Integer.valueOf(hallId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryZuoWeiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryZuoWeiBean bean) {
                        int maxh = 0;
                        int maxl = 0;
                        List<QueryZuoWeiBean.ResultBean> result = bean.getResult();
                        for (int i = 0; i < result.size(); i++){
                            if (maxh < Integer.valueOf(result.get(i).getRow())){
                                maxh = Integer.valueOf(result.get(i).getRow());
                            }
                            if (maxl < Integer.valueOf(result.get(i).getSeat())){
                                maxl = Integer.valueOf(result.get(i).getSeat());
                            }
                        }
                        //sele.setData(Integer.valueOf(result.get(i).getRow()),Integer.valueOf(result.get(i).getSeat()));
                        mSearchView.setScreenName(screeningHall);//设置屏幕名称
                        mSearchView.setMaxSelected(3);//设置最多选中
                        mSearchView.setSeatChecker(new SeatTable.SeatChecker() {
                            @Override
                            public boolean isValidSeat(int row, int column) {
                                if(column==2) {
                                    return false;
                                }
                                return true;
                            }
                            @Override
                            public boolean isSold(int row, int column) {
                                if(row==4&&column==6){
                                    return true;
                                }else if(row==6&&column==6){
                                    return true;
                                }
                                return false;
                            }
                            @Override
                            public void checked(int row, int column) {

                                a=row+"-"+column+",";
                                fare+=fare;
                                bu.setText("￥"+fare);
                                zhi.setText("￥"+fare);
                                Toast.makeText(SeatActivity.this, "排"+row+"列"+column, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void unCheck(int row, int column) {

                            }
                            @Override
                            public String[] checkedSeatTxt(int row, int column) {
                                return null;
                            }
                        });
                        mSearchView.setData(maxh,maxl);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    Handler mHandler = new Handler();
    private Map<String, String> result;
    @OnClick({R.id.bu,R.id.buu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bu:
            PayReq request = new PayReq();
            String substring = a.substring(0, a.length() - 1);
            String userid = SPUtil.getString(this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
            String sessionid = SPUtil.getString(this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
            String a = userid + "1" + "movie";
            String jia = MD5.jia(a);
            NetUtils.getInstance().getApis().doXiaDan(1, substring, jia)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<XiaDanBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(XiaDanBean xiaDanBean) {
                            orderId = xiaDanBean.getOrderId();
                            NetUtils.getInstance().getApis().doPay(1, orderId)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<PayBean>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(PayBean payBean) {
                                            //这里的appid，替换成自己的即可
                                            IWXAPI api = WXAPIFactory.createWXAPI(SeatActivity.this, "wxb3852e6a6b7d9516");
                                            api.registerApp("wxb3852e6a6b7d9516");

                                            //这里的bean，是服务器返回的json生成的bean
                                            PayReq payRequest = new PayReq();
                                            payRequest.appId = payBean.getAppId();
                                            payRequest.partnerId = payBean.getPartnerId();
                                            payRequest.prepayId = payBean.getPrepayId();
                                            payRequest.packageValue = "Sign=WXPay";//固定值
                                            payRequest.nonceStr = payBean.getNonceStr();
                                            payRequest.timeStamp = payBean.getTimeStamp();
                                            payRequest.sign = payBean.getSign();

                                            //发起请求，调起微信前去支付
                                            api.sendReq(payRequest);
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            break;
            case R.id.buu:
                Toast.makeText(this, "00", Toast.LENGTH_SHORT).show();
                NetUtils.getInstance().getApis().doPayZFB(2,orderId)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<ZFBBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(ZFBBean zfbBean) {
                       final String orderInfo = zfbBean.getResult();   // 订单信息

                       Runnable payRunnable = new Runnable() {
                           @Override
                           public void run() {
                               PayTask alipay = new PayTask(SeatActivity.this);
                               result = alipay.payV2(orderInfo,true);
                               Message msg = new Message();
                               msg.what = SDK_PAY_FLAG;
                               msg.obj = result;
                               mHandler.sendMessage(msg);
                           }
                       };
                       // 必须异步调用
                       Thread payThread = new Thread(payRunnable);
                       payThread.start();
                       PayResult payResult = new PayResult(result);
                       String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                       String resultStatus = payResult.getResultStatus();
                       // 判断resultStatus 为9000则代表支付成功
                       if (TextUtils.equals(resultStatus, "9000")) {
                           // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                           Toast.makeText(SeatActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
                break;
        }
    }



//    @OnClick(R.id.zhi)
//    public void onClickZhi(View view){
//        Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
//       NetUtils.getInstance().getApis().doPayZFB(2,orderId)
//               .subscribeOn(Schedulers.io())
//               .observeOn(AndroidSchedulers.mainThread())
//               .subscribe(new Observer<ZFBBean>() {
//                   @Override
//                   public void onSubscribe(Disposable d) {
//
//                   }
//
//                   @Override
//                   public void onNext(ZFBBean zfbBean) {
//                       final String orderInfo = zfbBean.getResult();   // 订单信息
//
//                       Runnable payRunnable = new Runnable() {
//                           @Override
//                           public void run() {
//                               PayTask alipay = new PayTask(SeatActivity.this);
//                               result = alipay.payV2(orderInfo,true);
//                               Message msg = new Message();
//                               msg.what = SDK_PAY_FLAG;
//                               msg.obj = result;
//                               mHandler.sendMessage(msg);
//                           }
//                       };
//                       // 必须异步调用
//                       Thread payThread = new Thread(payRunnable);
//                       payThread.start();
//                       PayResult payResult = new PayResult(result);
//                       String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                       String resultStatus = payResult.getResultStatus();
//                       // 判断resultStatus 为9000则代表支付成功
//                       if (TextUtils.equals(resultStatus, "9000")) {
//                           // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                           Toast.makeText(SeatActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                       }
//                   }
//
//                   @Override
//                   public void onError(Throwable e) {
//
//                   }
//
//                   @Override
//                   public void onComplete() {
//
//                   }
//               });
 //   }



}
