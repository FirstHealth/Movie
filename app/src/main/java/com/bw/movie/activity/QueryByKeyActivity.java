package com.bw.movie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.QueryAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.QueryByKeyBean;
import com.bw.movie.contract.QueryByKeyContract;
import com.bw.movie.presenter.QueryByKeyPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName QueryByKeyActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2222:21
 */
public class QueryByKeyActivity extends BaseActivity implements QueryByKeyContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.search)
    ImageView iv;
    @BindView(R.id.et)
    EditText et;
    @Override
    protected int getReasuce() {
        return R.layout.activity_querybukey;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new QueryByKeyPresenter(this);
    }

    @Override
    protected void getId() {

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Toast.makeText(this, ""+key, Toast.LENGTH_SHORT).show();

        BasePresenter presenter = getPresenter();
        if (presenter != null && presenter instanceof QueryByKeyPresenter){
            ((QueryByKeyPresenter) presenter).getData(key,1,5);
        }

    }

    @Override
    public void onSuccess(QueryByKeyBean bean) {
        List<QueryByKeyBean.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        QueryAdapter adapter = new QueryAdapter(this, result);
        rv.setAdapter(adapter);
    }

    @Override
    public void onError(String str) {

    }

    @OnClick(R.id.search)
    public void onClick(View view){
        String key = et.getText().toString();
        BasePresenter presenter = getPresenter();
        if (presenter != null && presenter instanceof QueryByKeyPresenter){
            ((QueryByKeyPresenter) presenter).getData(key,1,5);
        }
    }
}
