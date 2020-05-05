package com.bw.movie.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.adapter.MoviePingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieCinecisnBean;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.movieinfo.ResultBean_movieinfo;
import com.bw.movie.contract.CinecismContract;
import com.bw.movie.presenter.CinecismPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * @ClassName IntroduceFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2323:50
 */
public class CinecismFragment extends BaseFragment implements CinecismContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected int getReasuce() {
        return R.layout.item_cinecism;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new CinecismPresenter(this);
    }

    @Override
    protected void getid(View view) {

    }

    @Override
    protected void getData() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Override
    public void onSuccess(MovieCinecisnBean bean) {
        Log.i("xxxx","00");
        List<MovieCinecisnBean.ResultBean> result = bean.getResult();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        MoviePingAdapter adapter = new MoviePingAdapter(getContext(), result);
        rv.setAdapter(adapter);
    }

    @Override
    public void onError(String str) {
        Log.i("xxx",str);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(ResultBean_movieinfo bean){
        int movieId = new Long(bean.getMovieId()).intValue();
        BasePresenter presenter = getPresenter();
        if (presenter instanceof CinecismPresenter){
            ((CinecismPresenter) presenter).getData(movieId,1,5);
        }
    }
}
