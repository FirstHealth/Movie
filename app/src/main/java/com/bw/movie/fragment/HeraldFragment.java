package com.bw.movie.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.VideoAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieDataBean;

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
public class HeraldFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected int getReasuce() {
        return R.layout.item_herald;
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(MovieDataBean.ResultBean bean){
        List<MovieDataBean.ResultBean.ShortFilmListBean> shortFilmList = bean.getShortFilmList();
        Log.i("xxx",bean.getName()+"妹妹");
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        VideoAdapter adapter = new VideoAdapter(getContext(), shortFilmList);
        rv.setAdapter(adapter);
    }
}
