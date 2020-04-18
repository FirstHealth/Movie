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
                break;
                default:
        }

    }

    @Override
    public void getLoginSuccess(LoginBean bean) {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        SPUtil.putString(this,SPUtil.USERINFO_NAME,SPUtil.USERINFO_KEY_USER_ID,bean.getResult().getUserId()+"");
        SPUtil.putString(this,SPUtil.USERINFO_NAME,SPUtil.USERINFO_KEY_SESSION_ID,bean.getResult().getSessionId());

        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getLoginError(String str) {

    }

//    public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
//
//        private static final int RETURN_MSG_TYPE_LOGIN = 1;
//        private static final int RETURN_MSG_TYPE_SHARE = 2;
//
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            GlobalApplication.getInstance().iwxapi.handleIntent(getIntent(),WXEntryActivity.this);
//        }
//        @Override
//        public void onReq(BaseReq baseReq) {
//            switch(baseReq.getType()){
//                case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//                    break;
//                case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
//                    break;
//                default:
//                    break;
//            }
//        }
//        @Override
//        public void onResp(com.tencent.mm.opensdk.modelbase.BaseResp baseResp) {
//            Toast.makeText(this, "baseresp.getType = " + baseResp.getType(), Toast.LENGTH_SHORT).show();
//            switch (baseResp.errCode) {
//            case com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_OK:
//            switch (baseResp.getType()) {
//                case RETURN_MSG_TYPE_LOGIN:
////拿到了微信返回的code,立马再去请求access_token
//                String code = ((SendAuth.Resp) baseResp).code;
//                    try {
////获取access_token为http get请求
//                    String res= OkhttpUtils.getSyncAsString("https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppID&secret=AppSecret&code="+ code +"&grant_type=authorization_code");
//                    Toast.makeText(this, res, Toast.LENGTH_LONG).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Toast.makeText(this, code, Toast.LENGTH_LONG).show();
////就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
//                break;
//                case RETURN_MSG_TYPE_SHARE:
//                break;
//            }
//            break;
//            case com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_USER_CANCEL:
//            break;
//            case com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_AUTH_DENIED:
//            break;
//            case com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_UNSUPPORT:
//            break;
//            default:
//            break;
//}
//        }
//
//    }
}
