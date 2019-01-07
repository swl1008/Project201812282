package com.wd.tech.project20181228.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.bean.HomeSearchBean;

import java.util.ArrayList;
import java.util.List;

public class HomeSearchAdapter extends RecyclerView.Adapter<HomeSearchAdapter.ViewHolder> {
    private Context context;
    private List<HomeSearchBean.ResultBean> list;

    public HomeSearchAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<HomeSearchBean.ResultBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_gods_search,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.text_name.setText(list.get(i).getCommodityName());
        viewHolder.text_price.setText("￥"+list.get(i).getPrice()+"");
        viewHolder.text_saleNum.setText(list.get(i).getSaleNum()+"");
        Uri uri = Uri.parse(list.get(i).getMasterPic());
        viewHolder.draweeView.setImageURI(uri);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null){
                    int commodityId = list.get(i).getCommodityId();
                    itemClickListener.onClick(commodityId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;
        TextView text_name,text_price,text_saleNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            draweeView = itemView.findViewById(R.id.home_search_image);
            text_name = itemView.findViewById(R.id.home_search_name);
            text_price = itemView.findViewById(R.id.home_search_price);
            text_saleNum = itemView.findViewById(R.id.home_search_saleNum);
        }
    }

    private onItemClickListener itemClickListener;

    public void setOnItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface onItemClickListener{
        void onClick(int commodityId);
    }

}
