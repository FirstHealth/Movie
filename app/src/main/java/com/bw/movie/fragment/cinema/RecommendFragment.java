package com.bw.movie.fragment.cinema;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailsActivity;
import com.bw.movie.adapter.RecomeAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.RecomeBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName RecommendFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/5/521:43
 */
public class RecommendFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected int getReasuce() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getid(View view) {


    }

    @Override
    protected void getData() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        NetUtils.getInstance().getApis().doRecome(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecomeBean recomeBean) {
                        List<RecomeBean.ResultBean> result = recomeBean.getResult();
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rv.setLayoutManager(manager);
                        RecomeAdapter adapter = new RecomeAdapter(getContext(), result);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("xxx",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getD(RecomeBean.ResultBean bean){
        int id = bean.getId();
        SPUtil.putString(getContext(),"login","cinemaId",id+"");
        Intent intent = new Intent(getContext(), CinemaDetailsActivity.class);
        startActivity(intent);
    }
}
