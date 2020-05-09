package com.bw.movie.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.MyFocusActivity;
import com.bw.movie.activity.PersonalinformationActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @ClassName MovieFragment
 * @Description TODO
 * @Author tys
 * @Date 2020/4/1823:02
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_mine_userinfo)
    ImageView ivMineUserinfo;
    @BindView(R.id.ticket)
    RelativeLayout ticket;
    @BindView(R.id.iv_mine_follow)
    ImageView ivMineFollow;
    @BindView(R.id.iv_mine_order)
    ImageView ivMineOrder;
    @BindView(R.id.iv_mine_record)
    ImageView ivMineRecord;
    @BindView(R.id.iv_mine_history)
    ImageView ivMineHistory;
    @BindView(R.id.iv_mine_comment)
    ImageView ivMineComment;
    @BindView(R.id.iv_mine_idea)
    ImageView ivMineIdea;
    @BindView(R.id.iv_mine_setting)
    ImageView ivMineSetting;
    Unbinder unbinder;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.iv_mine_systemmsg)
    ImageView ivMineSystemmsg;
    @BindView(R.id.iv_mine_head)
    SimpleDraweeView ivMineHead;
    @BindView(R.id.rl1)
    RelativeLayout rll;
    @Override
    protected int getReasuce() {
        return R.layout.fragment_mine;
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

    }

    @OnClick(R.id.rl1)
    public void setRll(){
        Intent intent = new Intent(getActivity(), PersonalinformationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_mine_follow)
    public void setiv_gz(){
        Intent intent = new Intent(getActivity(), MyFocusActivity.class);
        startActivity(intent);
    }
}
