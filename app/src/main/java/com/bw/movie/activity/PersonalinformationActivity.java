package com.bw.movie.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.UploadHeadPicBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class PersonalinformationActivity extends BaseActivity {

    @BindView(R.id.iv_mine_userinfo_back)
    ImageView iv_fh;
    @BindView(R.id.iv_mine_userinfo)
    SimpleDraweeView iv_sdv;
    @BindView(R.id.tv_mine_userinfo_name)
    TextView tv_name;
    @BindView(R.id.tv_mine_userinfo_sex)
    TextView tv_sex;
    @BindView(R.id.tv_mine_userinfo_time)
    TextView tv_time;
    @BindView(R.id.tv_mine_userinfo_phone)
    TextView tv_phone;
    @BindView(R.id.tv_mine_userinfo_eamil)
    TextView tv_eamil;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

    @Override
    protected int getReasuce() {
        return R.layout.activity_personalinformation;
    }

    @Override
    protected void getData() {
        String headPic = SPUtil.getString(this, "mine", "head");
        Uri parse = Uri.parse(headPic);
        iv_sdv.setImageURI(parse);
        String nickName = SPUtil.getString(this, "mine", "nick");
        tv_name.setText(nickName);
        String sex = SPUtil.getString(this, "mine", "sex");
        if(Integer.valueOf(sex)==1){
            tv_sex.setText("男");
        }else{
            tv_sex.setText("女");
        }
        String lastLoginTime = SPUtil.getString(this, "mine", "lasetime");
        Date date = new Date(Long.valueOf(lastLoginTime));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        tv_time.setText(format);
        tv_phone.setText("18531970072");
        String email = SPUtil.getString(this, "mine", "email");
        tv_eamil.setText(email);
    }

    @OnClick(R.id.iv_mine_userinfo_back)
    public void setIv_fh(){
        finish();
    }

    @OnClick(R.id.iv_mine_userinfo)
    public void setIv_sdv(){
        PictureSelector
                .create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 200, 200, 1, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String userId = SPUtil.getString(PersonalinformationActivity.this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_USER_ID);
        String sessionId = SPUtil.getString(PersonalinformationActivity.this, SPUtil.USERINFO_NAME, SPUtil.USERINFO_KEY_SESSION_ID);
        if (resultCode==RESULT_OK&&requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if (data != null) {
                String stringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                File file = new File(stringExtra);
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                HashMap<String, String> map = new HashMap<>();
                RequestBody requsetBody = NetUtils.getInstance().getRequsetBody(files, map);
                NetUtils.getInstance().getApis().getUpLoadHeadPicBean(Integer.valueOf(userId),sessionId,requsetBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UploadHeadPicBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UploadHeadPicBean upLoadHeadPicBean) {
                                Toast.makeText(PersonalinformationActivity.this, ""+upLoadHeadPicBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }

    }
}
