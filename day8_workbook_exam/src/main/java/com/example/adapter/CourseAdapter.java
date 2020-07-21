package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bean.HomeBean;
import com.example.day8_workbook_exam.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    List<HomeBean.BodyBean.ResultBean> resultBeanList;
    Context context;

    public CourseAdapter(List<HomeBean.BodyBean.ResultBean> resultBeanList, Context context) {
        this.resultBeanList = resultBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.course_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeBean.BodyBean.ResultBean resultBean = resultBeanList.get(i);

        viewHolder.name.setText(resultBean.getTeacherName());
        viewHolder.title.setText(resultBean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener != null) {
                    onClickItemListener.clickItem();
                }
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
        return resultBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
        }
    }
}
