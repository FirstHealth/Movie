package com.bw.movie.fragment;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.JuZhaoAdapter;
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
public class StillFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected int getReasuce() {
        return R.layout.item_still;
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
        List<String> posterList = bean.getPosterList();

        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        rv.setLayoutManager(manager);
        JuZhaoAdapter zhaoAdapter = new JuZhaoAdapter(getContext(), posterList);
        rv.setAdapter(zhaoAdapter);

        rv.addItemDecoration(new SpaceItemDecoration(10));
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
}
