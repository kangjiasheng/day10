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
import com.example.bean.NewsDbBean;
import com.example.day2_workbook_exam.R;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter {
    List<NewsDbBean> collectionListData;
    Context context;
    ;

    final int ONE_CODE = 1;
    final int TWO_CODE = 2;

    public CollectionAdapter(List<NewsDbBean> collectionListData, Context context) {
        this.collectionListData = collectionListData;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return ONE_CODE;
        return TWO_CODE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ONE_CODE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.one_item, viewGroup, false);
            return new OneViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.two_item, viewGroup, false);
            return new TwoViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        NewsDbBean newsDbBean = collectionListData.get(i);
        switch (type) {
            case ONE_CODE:
                OneViewHolder oneViewHolder = (OneViewHolder) viewHolder;

                Glide.with(context).load(newsDbBean.getThumbnail()).into(oneViewHolder.img);
                oneViewHolder.title.setText(newsDbBean.getTitle());

                break;
            case TWO_CODE:
                TwoViewHolder twoViewHolder = (TwoViewHolder) viewHolder;

                twoViewHolder.title.setText(newsDbBean.getTitle());
                Glide.with(context).load(newsDbBean.getThumbnail()).into(twoViewHolder.img);

                break;
        }
    }

    @Override
    public int getItemCount() {
        return collectionListData.size();
    }

    private class OneViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.one_img);
            title = itemView.findViewById(R.id.one_title);
        }
    }

    private class TwoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.two_title);
            img = itemView.findViewById(R.id.two_img);
        }
    }
}
