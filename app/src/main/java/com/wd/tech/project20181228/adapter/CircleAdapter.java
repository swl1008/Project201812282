package com.wd.tech.project20181228.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.bean.CircleBean;

import java.util.ArrayList;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private Context context;
    private List<CircleBean.ResultBean> list;

    public CircleAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<CircleBean.ResultBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circle_item_image_one,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.text_name.setText(list.get(i).getNickName());
        viewHolder.text_date.setText(list.get(i).getCreateTime()+"");
        viewHolder.text_message.setText(list.get(i).getContent());
        viewHolder.text_skr.setText(list.get(i).getGreatNum()+"");
        Uri image_head = Uri.parse(list.get(i).getHeadPic());
        viewHolder.image_head.setImageURI(image_head);
        Uri image_one = Uri.parse(list.get(i).getImage());
        viewHolder.image_one.setImageURI(image_one);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image_head,image_one;
        ImageView image_prise;
        TextView text_name,text_date,text_message,text_skr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_head = itemView.findViewById(R.id.circle_item_image_head);
            image_one = itemView.findViewById(R.id.circle_item_image_one);
            image_prise = itemView.findViewById(R.id.circle_item_image_prise);
            text_name = itemView.findViewById(R.id.circle_item_text_name);
            text_date = itemView.findViewById(R.id.circle_item_text_date);
            text_message = itemView.findViewById(R.id.circle_item_text_message);
            text_skr = itemView.findViewById(R.id.circle_item_text_skr);
        }
    }

}
