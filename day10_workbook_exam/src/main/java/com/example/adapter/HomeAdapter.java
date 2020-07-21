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
import com.example.day10_workbook_exam.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    Context context;
    List<HomeBean.DataBean.DatasBean> datasBeanList;

    public HomeAdapter(Context context, List<HomeBean.DataBean.DatasBean> datasBeanList) {
        this.context = context;
        this.datasBeanList = datasBeanList;
    }

    public static final int ONE_CODE = 1;
    public static final int TWO_CODE = 2;

    @Override
    public int getItemViewType(int position) {
        if (position % 6 == 0) {
            return TWO_CODE;
        } else {
            return ONE_CODE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case ONE_CODE:
                View inflate = LayoutInflater.from(context).inflate(R.layout.one_item, viewGroup, false);
                return new OneViewHolder(inflate);
            case TWO_CODE:
                View inflate1 = LayoutInflater.from(context).inflate(R.layout.two_item, viewGroup, false);
                return new TwoViewHolder(inflate1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final HomeBean.DataBean.DatasBean datasBean = datasBeanList.get(i);
        int viewType = getItemViewType(i);
        switch (viewType) {
            case ONE_CODE:
                OneViewHolder oneViewHolder = (OneViewHolder) viewHolder;
                oneViewHolder.title.setText(datasBean.getTitle());
                Glide.with(context).load(datasBean.getEnvelopePic()).into(oneViewHolder.img);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickItemListener != null) {
                            onClickItemListener.clickItem(i);
                        }
                    }
                });
                break;
            case TWO_CODE:
                Glide.with(context).load(datasBean.getEnvelopePic()).into(((TwoViewHolder) viewHolder).img);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickItemListener != null) {
                            onClickItemListener.clickItem(i);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datasBeanList.size();
    }

    private class OneViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.one_img);
            title = itemView.findViewById(R.id.title);

        }
    }

    private class TwoViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
        }
    }

    OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface OnClickItemListener {
        void clickItem(int position);
    }
}
