package com.bw.movie.fragment;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.Reseasing2Adapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ReleaseingMovie;
import com.bw.movie.bean.reselea.ResultBean_reselea;
import com.bw.movie.contract.ReseaingContract;
import com.bw.movie.presenter.ReseaingPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName ReseaingFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2122:09
 */
public class ReseaingFragment extends BaseFragment implements ReseaingContract.IView {
    @BindView(R.id.rv)
    XRecyclerView rv;
    ArrayList<ResultBean_reselea> list = new ArrayList();
    int a = 1;
    @Override
    protected int getReasuce() {
        return R.layout.fragment_resaing;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new ReseaingPresenter(this);
    }

    @Override
    protected void getid(View view) {

    }

    @Override
    protected void getData() {
        getd(1,5);
        rv.setPullRefreshEnabled(true);
        rv.setLoadingMoreEnabled(true);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                a = 1;
                getd(1,5);
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                a++;
                getd(a,5);
                rv.loadMoreComplete();
            }
        });
    }

    @Override
    public void onReleaseingSuccess(ReleaseingMovie bean) {
        List<ResultBean_reselea> result = bean.getResult();
        if (result != null) {
            list.addAll(result);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        Reseasing2Adapter adapter = new Reseasing2Adapter(getContext(), list);
        rv.setAdapter(adapter);

        rv.addItemDecoration(new SpaceItemDecoration(10));

    }

    @Override
    public void onReleaseingError(String str) {
        Log.i("xxx",str);
    }

    public void getd(int page,int count){
        BasePresenter presenter = getPresenter();
        if (presenter instanceof ReseaingPresenter){
            ((ReseaingPresenter) presenter).getReleaseingData(page, count);
        }
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
