package com.bw.movie.fragment.cinema;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.LeftAdapter;
import com.bw.movie.adapter.RightAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AreaBean1;
import com.bw.movie.bean.NearBean2;
import com.bw.movie.utils.NetUtils;

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
public class AreaFragment extends BaseFragment {
    @BindView(R.id.rv_left)
    RecyclerView rv_left;
    @BindView(R.id.rv_right)
    RecyclerView rv_right;
    @Override
    protected int getReasuce() {
        return R.layout.fragment_area;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getid(View view) {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void getData() {
        NetUtils.getInstance().getApis().doAreaOne()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaBean1>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AreaBean1 areaBean1) {
                        List<AreaBean1.ResultBean> result = areaBean1.getResult();
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rv_left.setLayoutManager(manager);
                        LeftAdapter adapter = new LeftAdapter(getContext(), result);
                        rv_left.setAdapter(adapter);
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
    public void getd(AreaBean1.ResultBean bean){
        int regionId = bean.getRegionId();
        NetUtils.getInstance().getApis().doNear2(regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearBean2>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearBean2 nearBean2) {
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rv_right.setLayoutManager(manager);
                        List<NearBean2.ResultBean> result = nearBean2.getResult();
                        RightAdapter adapter = new RightAdapter(getContext(), result);
                        rv_right.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
