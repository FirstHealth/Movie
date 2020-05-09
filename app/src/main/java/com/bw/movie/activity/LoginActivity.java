package com.bw.movie.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.LoginContract;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.utils.App;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.internal.Constants;

public class LoginActivity extends BaseActivity implements LoginContract.IView {
    @BindView(R.id.gozhu)
    TextView gozhu;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.weixin)
    Button weixin;
    @Override
    protected int getReasuce() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void getId() {

    }

    @Override
    protected void getData() {

    }

    @OnClick({R.id.gozhu,R.id.login,R.id.weixin})
    public void onClick(View view){


        switch (view.getId()){
            case R.id.gozhu:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login:
                String email = this.email.getText().toString();
                String pass = this.pass.getText().toString();
                String encrypt = EncryptUtil.encrypt(pass);
                if (NetUtils.getInstance().isNetWork(this)){
                    BasePresenter presenter = getPresenter();
                    if (presenter != null && presenter instanceof LoginPresenter){
                        ((LoginPresenter) presenter).getData(email,encrypt);
                    }
                }else {
                    Toast.makeText(this, "无网络，请稍后再试", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.weixin:
                IWXAPI api = App.getIWXAPI();
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
                break;
                default:
        }

    }

    @Override
    public void getLoginSuccess(LoginBean bean) {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        SPUtil.putString(this,SPUtil.USERINFO_NAME,SPUtil.USERINFO_KEY_USER_ID,bean.getResult().getUserId()+"");
        SPUtil.putString(this,SPUtil.USERINFO_NAME,SPUtil.USERINFO_KEY_SESSION_ID,bean.getResult().getSessionId());
        SPUtil.putString(this,"mine","head",bean.getResult().getUserInfo().getHeadPic());
        SPUtil.putString(this,"mine","nick",bean.getResult().getUserInfo().getNickName());
        SPUtil.putString(this,"mine","phone",bean.getResult().getUserInfo().getPhone());
        SPUtil.putString(this,"mine","sex",bean.getResult().getUserInfo().getSex()+"");
        SPUtil.putString(this,"mine","birthday",bean.getResult().getUserInfo().getBirthday()+"");
        SPUtil.putString(this,"mine","lasetime",bean.getResult().getUserInfo().getLastLoginTime()+"");
        SPUtil.putString(this,"mine","email",email.getText().toString());
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getLoginError(String str) {

    }

}
