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
import com.example.day6_workbook_exam.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    List<HomeBean.T1348654151579Bean> t1348654151579BeanList;
    Context context;

    final int NULL_IMAGE = 1;
    final int HAVE_IMAGE = 2;

    public HomeAdapter(List<HomeBean.T1348654151579Bean> t1348654151579BeanList, Context context) {
        this.t1348654151579BeanList = t1348654151579BeanList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return NULL_IMAGE;
        } else {
            return HAVE_IMAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == NULL_IMAGE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.null_img, viewGroup, false);
            return new NullImgViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.img_item, viewGroup, false);
            return new HaveImgViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        HomeBean.T1348654151579Bean t1348654151579Bean = t1348654151579BeanList.get(i);
        switch (type) {
            case NULL_IMAGE:

                NullImgViewHolder nullImgViewHolder = (NullImgViewHolder) viewHolder;

                nullImgViewHolder.author.setText(t1348654151579Bean.getSource());
                nullImgViewHolder.time.setText(t1348654151579Bean.getMtime());
                nullImgViewHolder.title.setText(t1348654151579Bean.getTitle());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickItemListener!=null){
                            onClickItemListener.clickItem(i);
                        }
                    }
                });

                break;
            case HAVE_IMAGE:

                HaveImgViewHolder haveImgViewHolder = (HaveImgViewHolder) viewHolder;

                haveImgViewHolder.author.setText(t1348654151579Bean.getSource());
                haveImgViewHolder.time.setText(t1348654151579Bean.getMtime());
                haveImgViewHolder.title.setText(t1348654151579Bean.getTitle());
                Glide.with(context).load(t1348654151579Bean.getImgsrc()).into(haveImgViewHolder.img);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickItemListener!=null){
                            onClickItemListener.clickItem(i);
                        }
                    }
                });

                break;
        }
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
        return t1348654151579BeanList.size();
    }

    private class NullImgViewHolder extends RecyclerView.ViewHolder {
        TextView author, time, title;

        public NullImgViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.one_author);
            time = itemView.findViewById(R.id.one_time);
            title = itemView.findViewById(R.id.one_title);
        }
    }

    private class HaveImgViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView author, time, title;

        public HaveImgViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            author = itemView.findViewById(R.id.two_author);
            time = itemView.findViewById(R.id.two_time);
            title = itemView.findViewById(R.id.two_title);
        }
    }
}
