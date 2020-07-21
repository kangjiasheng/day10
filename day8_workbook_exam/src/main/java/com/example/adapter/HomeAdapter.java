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
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.HomeBean;
import com.example.day8_workbook_exam.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<HomeBean.BodyBean.ResultBean> resultBeanList;
    Context context;

    public HomeAdapter(List<HomeBean.BodyBean.ResultBean> resultBeanList, Context context) {
        this.resultBeanList = resultBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        HomeBean.BodyBean.ResultBean resultBean = resultBeanList.get(i);

        RequestOptions circleCropTransform = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(resultBean.getTeacherPic())
                .apply(circleCropTransform)
                .into(viewHolder.img);
        viewHolder.name.setText(resultBean.getTeacherName());
        viewHolder.title.setText(resultBean.getTitle());

//        viewHolder.type.setText(resultBeanList.get(i).getTeacherType().get(i).getTypename());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener != null)
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
        return resultBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, title, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
        }
    }
}
