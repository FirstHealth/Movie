package com.bw.movie.fragment;

import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.HotAdapter;
import com.bw.movie.adapter.ReseasingAdapter;
import com.bw.movie.adapter.UpCommingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HotMovie;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.UpcomingBean;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.presenter.MoviePresenter;
import com.bw.movie.utils.NetUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName MovieFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1823:02
 */
public class MovieFragment extends BaseFragment implements MovieContract.IView {
    @BindView(R.id.xb)
    XBanner xb;
    @BindView(R.id.rv_hot)
    RecyclerView rv;
    @BindView(R.id.rv_reseasing)
    RecyclerView rv2;
    @BindView(R.id.rv_upcomming)
    RecyclerView rv3;
    ArrayList<Integer> list = new ArrayList<>();
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

    }

    @Override
    protected void getData() {
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
        }

    }

    @Override
    public void onHotSuccess(HotMovie bean) {
        List<HotMovie.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        HotAdapter hotAdapter = new HotAdapter(getContext(), result);
        rv.setAdapter(hotAdapter);
        rv.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public void onHotError(String str) {

    }

    @Override
    public void onReleaseingSuccess(ReleaseingMovie bean) {
        List<ReleaseingMovie.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(manager);
        ReseasingAdapter reseasingAdapter = new ReseasingAdapter(getContext(), result);
        rv2.setAdapter(reseasingAdapter);
        rv2.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public void onReleaseingError(String str) {

    }

    @Override
    public void onUpcommingSuccess(UpcomingBean bean) {
        List<UpcomingBean.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv3.setLayoutManager(manager);
        UpCommingAdapter commingAdapter = new UpCommingAdapter(getContext(), result);
        rv3.setAdapter(commingAdapter);
        rv3.addItemDecoration(new SpaceItemDecoration(20));
    }

    @Override
    public void onUpcommingError(String str) {

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
