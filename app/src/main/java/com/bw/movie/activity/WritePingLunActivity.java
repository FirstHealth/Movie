package com.bw.movie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.WritePingLunContract;
import com.bw.movie.presenter.WritePingLunPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName WritePingLunActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2723:30
 */
public class WritePingLunActivity extends BaseActivity implements WritePingLunContract.IView {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.fen)
    TextView fen;
    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.tv)
    EditText tv;
    @BindView(R.id.bu)
    Button bu;
    private String id;
    double a = 0.0;
    private String name1;

    @Override
    protected int getReasuce() {
        return R.layout.activity_writeping;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new WritePingLunPresenter(this);
    }

    @Override
    protected void getId() {

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name1 = intent.getStringExtra("name");

        name.setText(name1);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                fen.setText("我的评分 ("+rating+"分)");
                a = rating;
            }
        });
    }

    @Override
    public void onSuccess(RegistBean bean) {
        Toast.makeText(this, "评论成功，快去查看吧", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String str) {

    }

    @OnClick(R.id.bu)
    public void onClick(View view){
        String s = tv.getText().toString();
        BasePresenter presenter = getPresenter();
        if (presenter instanceof WritePingLunPresenter){
            ((WritePingLunPresenter) presenter).getData(Integer.valueOf(id),s,a);
        }
    }
}
