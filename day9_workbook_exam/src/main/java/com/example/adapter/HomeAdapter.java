package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.HomeBean;
import com.example.day9_workbook_exam.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<HomeBean.DataBean.DatasBean> homeBeanList;
    Context context;

    public HomeAdapter(List<HomeBean.DataBean.DatasBean> homeBeanList, Context context) {
        this.homeBeanList = homeBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeBean.DataBean.DatasBean datasBean = homeBeanList.get(i);

        Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.img);
        viewHolder.title.setText(datasBean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener != null)
                    onClickItemListener.clickItem();
            }
        });
    }

    OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface OnClickItemListener {
        void clickItem();
    }

    @Override
    public int getItemCount() {
        return homeBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
