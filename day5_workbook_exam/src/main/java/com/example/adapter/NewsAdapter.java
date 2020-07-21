package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.NewsBean;
import com.example.day5_workbook_exam.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsBean.T1348647909107Bean> t1348647909107BeanList;
    Context context;
    public boolean isShow;

    public NewsAdapter(List<NewsBean.T1348647909107Bean> t1348647909107BeanList, Context context) {
        this.t1348647909107BeanList = t1348647909107BeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.news_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final NewsBean.T1348647909107Bean t1348647909107Bean = t1348647909107BeanList.get(i);

        Glide.with(context).load(t1348647909107Bean.getImgsrc()).into(viewHolder.img);
        viewHolder.title.setText(t1348647909107Bean.getTitle());

        if (isShow == true) {//显示checkBox
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        } else {//隐藏checkBox
            viewHolder.checkBox.setVisibility(View.INVISIBLE);
        }

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    //设置成勾选状态
                    t1348647909107Bean.setCheckState(true);
                } else {
                    //设置成未勾选状态
                    t1348647909107Bean.setCheckState(false);
                }
            }
        });
        //设置是否勾选
        viewHolder.checkBox.setChecked(t1348647909107Bean.isCheckState());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.clickItem(i);
            }
        });
    }

    OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface OnClickItemListener {
        void clickItem(int position);
    }

    @Override
    public int getItemCount() {
        return t1348647909107BeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkbox_delete);
        }
    }
}
