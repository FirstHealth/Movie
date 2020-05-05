package com.bw.movie.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.YanAdapter;
import com.bw.movie.adapter.YuanAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.movieinfo.MovieActorBean;
import com.bw.movie.bean.movieinfo.MovieDirectorBean;
import com.bw.movie.bean.movieinfo.ResultBean_movieinfo;

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
public class IntroduceFragment extends BaseFragment {
    @BindView(R.id.jian)
    TextView jian;
    @BindView(R.id.daosize)
    TextView daosize;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.yansize)
    TextView yansize;
    @BindView(R.id.rv1)
    RecyclerView rv1;
    ResultBean_movieinfo bean1 = new ResultBean_movieinfo();
    @Override
    protected int getReasuce() {
        return R.layout.item_introduce;
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
        if (bean1.getName() != null) {
            Log.i("xxx", bean1.getName());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(ResultBean_movieinfo bean){
        String summary = bean.getSummary();
        jian.setText(summary);

        List<MovieDirectorBean> movieDirector = bean.getMovieDirector();
        daosize.setText("导演（"+movieDirector.size()+"）");
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        YanAdapter yanAdapter = new YanAdapter(getContext(), movieDirector);
        rv.setAdapter(yanAdapter);
        List<MovieActorBean> movieActor = bean.getMovieActor();
        yansize.setText("演员（"+movieActor.size()+"）");
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv1.setLayoutManager(manager1);
        YuanAdapter yuanAdapter = new YuanAdapter(getContext(), movieActor);
        rv1.setAdapter(yuanAdapter);
    }
}
