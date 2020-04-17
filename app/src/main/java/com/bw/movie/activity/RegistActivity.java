package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.RegistContract;
import com.bw.movie.presenter.RegistPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity implements RegistContract.IView {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.gologin)
    TextView goLogin;
    @BindView(R.id.get)
    Button getYzm;
    @BindView(R.id.yzm)
    EditText yzm;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.group)
    ImageView group;
    @BindView(R.id.yanjing)
    ImageView eye;
    @Override
    protected int getReasuce() {
        return R.layout.activity_regist;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new RegistPresenter(this);
    }

    @Override
    protected void getId() {

    }

    @Override
    protected void getData() {

    }

    @OnClick({R.id.gologin,R.id.get,R.id.regist,R.id.group,R.id.yanjing})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.gologin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.get:
                String ema = this.email.getText().toString();

                if (NetUtils.getInstance().isNetWork(this)){
                    BasePresenter presenter = getPresenter();
                    if (presenter!=null && presenter instanceof RegistPresenter){
                        ((RegistPresenter) presenter).getEmailData(ema);
                    }
                }else {
                    Toast.makeText(this, "无网络，请稍后再试", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.regist:
                if (NetUtils.getInstance().isNetWork(this)){
                    String name = this.name.getText().toString();
                    String email = this.email.getText().toString();
                    String pass = this.pass.getText().toString();
                    String yzm = this.yzm.getText().toString();
                    String encrypt = EncryptUtil.encrypt(pass);

                    BasePresenter presenter = getPresenter();
                    if (presenter!=null && presenter instanceof RegistPresenter){
                        ((RegistPresenter) presenter).getRegistData(name,encrypt,email,yzm);
                    }

                }else {
                    Toast.makeText(this, "请检查网络，稍后再试", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.group:
                break;
            case R.id.yanjing:
                break;
                default:
        }
    }

    @Override
    public void doRegistSuccess(RegistBean bean) {
        Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void doRegistError(String str) {

    }

    @Override
    public void doEmailSuccess(EmailBean bean) {
        Toast.makeText(this, "验证码已发送，请注意接收", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doEmailError(String str) {
        Toast.makeText(this, "连接超时，请重新发送", Toast.LENGTH_SHORT).show();
    }
}
