package com.bw.movie.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.utils.NetUtils;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName JuZhaoAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/241:16
 */
public class JuZhaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> list;
    private View view;
    private Bitmap bitmap;
    boolean toBig = false;
    private ImageView iv;
    private TextView tv;
    private Dialog mLoadingDialog;

    public JuZhaoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.item_jizhao, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String s = list.get(position);
        Uri uri = Uri.parse(s);
        ((ViewHolder)holder).iv.setImageURI(uri);

        ((ViewHolder)holder).iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new Dialog(context);
                    if (mLoadingDialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(s).into(iv);
                        mLoadingDialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        mLoadingDialog.show();
                    }
                }else {
                    Dialog dialog = new Dialog(context);
                    if (dialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(s).into(iv);
                        dialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        dialog.show();
                    }
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
