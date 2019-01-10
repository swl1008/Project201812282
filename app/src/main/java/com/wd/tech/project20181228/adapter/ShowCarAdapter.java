package com.wd.tech.project20181228.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.bean.ShowCarBean;

import java.util.ArrayList;
import java.util.List;

public class ShowCarAdapter extends RecyclerView.Adapter<ShowCarAdapter.ViewHolder> {
    private Context context;
    private List<ShowCarBean.ResultBean> list;

    public ShowCarAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<ShowCarBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item_show,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.item_text_name.setText(list.get(i).getCommodityName());
        viewHolder.item_text_price.setText("￥"+list.get(i).getPrice()+"");
        viewHolder.item_text_num.setText(list.get(i).getCount()+"");
        Uri uri = Uri.parse(list.get(i).getPic());
        viewHolder.item_image.setImageURI(uri);

        list.get(i).setCheck(list.get(i).isCheck());

        viewHolder.item_checkbox.setChecked(list.get(i).isCheck());

        viewHolder.item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setCheck(viewHolder.item_checkbox.isChecked());
                if (checkListener!=null){
                    checkListener.setOnCkecked(list);
                }
            }
        });

        viewHolder.item_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setCount(list.get(i).getCount() + 1);
                viewHolder.item_text_num.setText(list.get(i).getCount()+"");
                if (checkListener!=null){
                    checkListener.setOnCkecked(list);
                }
            }
        });
        viewHolder.item_button_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).getCount() >1){
                    list.get(i).setCount(list.get(i).getCount() - 1);
                    viewHolder.item_text_num.setText(list.get(i).getCount()+"");
                    if (checkListener != null){
                        checkListener.setOnCkecked(list);
                    }
                }
            }
        });

    }

    public void setChecked(boolean checked) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(checked);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox item_checkbox;
        SimpleDraweeView item_image;
        TextView item_text_name,item_text_price,item_text_num;
        Button item_button_add,item_button_reduce;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_checkbox = itemView.findViewById(R.id.car_item_show_checkbox);
            item_image = itemView.findViewById(R.id.car_item_show_image);
            item_text_name = itemView.findViewById(R.id.car_item_show_name);
            item_text_price = itemView.findViewById(R.id.car_item_show_price);
            item_text_num = itemView.findViewById(R.id.car_item_show_text_num);
            item_button_add = itemView.findViewById(R.id.car_item_show_button_add);
            item_button_reduce = itemView.findViewById(R.id.car_item_show_button_reduce);

        }
    }

    private onCheckListener checkListener;

    public void listener(onCheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public interface onCheckListener{
        void setOnCkecked(List<ShowCarBean.ResultBean> mList);
    }
}
