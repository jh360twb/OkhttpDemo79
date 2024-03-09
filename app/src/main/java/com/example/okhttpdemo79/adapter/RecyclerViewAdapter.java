package com.example.okhttpdemo79.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.okhttpdemo79.Constant;
import com.example.okhttpdemo79.R;
import com.example.okhttpdemo79.beans.GetResult;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Tian
 * @description
 * @date :2020/7/9 10:21
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.InnerHolder> {
    Context mContext;
    private List<GetResult.DataBean> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_result,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        GetResult.DataBean dataBean = mData.get(position);
        holder.itemTitle.setText(dataBean.getTitle());
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext).load(Constant.base_url+dataBean.getCover()).apply(requestOptions).into(holder.itemPic);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<GetResult.DataBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ImageView itemPic;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemPic = itemView.findViewById(R.id.item_pic);
        }
    }
}
