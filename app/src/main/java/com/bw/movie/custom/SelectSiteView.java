package com.bw.movie.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bw.movie.bean.SiteBean;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectSiteView extends LinearLayout {
    private Context mContext;
    HashMap<Integer, LinearLayout> layoutMap = new HashMap<>();
    private ArrayList<SiteBean> mSiteBeanList;

    public SelectSiteView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SelectSiteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView(){
        setOrientation(VERTICAL);
    }

    public void setData(ArrayList<SiteBean> siteBeanList){
        mSiteBeanList = siteBeanList;

        for(final SiteBean siteBean: mSiteBeanList){
            int rowSite = siteBean.getRow();
            //一行
            LinearLayout linearLayout = null;
            if(layoutMap.get(rowSite) == null){
                linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(HORIZONTAL);
                layoutMap.put(rowSite, linearLayout);
                addView(linearLayout);
            }else{
                linearLayout = layoutMap.get(rowSite);
            }

            //座位的圆形图案
            View view = new View(mContext);
            if(siteBean.getStatus() == SiteBean.SITE_CAN_SELECT){
                view.setBackgroundColor(Color.WHITE);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(siteBean.getStatus() == SiteBean.SITE_CAN_SELECT){
                            siteBean.setStatus(SiteBean.SITE_IS_SELECTED);
                        }else if(siteBean.getStatus() == SiteBean.SITE_IS_SELECTED){
                            siteBean.setStatus(SiteBean.SITE_CAN_SELECT);
                        }
                    }
                });
            }else if(siteBean.getStatus() == SiteBean.SITE_HAS_SAILED){
                view.setBackgroundColor(Color.YELLOW);
            }

            linearLayout.addView(view);
        }
    }

    public ArrayList<SiteBean> getSite(){
        return mSiteBeanList;
    }
}
