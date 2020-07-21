package com.example.day8_workbook_exam;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.DetailsBean;
import com.example.bean.HomeBean;
import com.example.fragment.CourseFragment;
import com.example.net.ApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private int id = 191;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -1);
        Log.e(TAG, "onCreate: " + id);
        initView();
        initData();
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.detailsUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getDetailsData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        List<DetailsBean.BodyBean.ResultBean> result = detailsBean.getBody().getResult();
                        Log.e(TAG, "onNext: 111111111111" + result.get(0).getDescription());

                        final ArrayList<Fragment> fragments = new ArrayList<>();
                        for (int i = 0; i < result.size(); i++) {
                            if (i == 0) {
                                CourseFragment courseFragment = new CourseFragment();
                                fragments.add(courseFragment);
                            } else if (i == 1) {
                                CourseFragment courseFragment = new CourseFragment();
                                fragments.add(courseFragment);
                            } else if (i == 2) {
                                CourseFragment courseFragment = new CourseFragment();
                                fragments.add(courseFragment);
                            }
                        }

                        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                            @Override
                            public Fragment getItem(int i) {
                                return fragments.get(i);
                            }

                            @Override
                            public int getCount() {
                                return fragments.size();
                            }
                        });

                        tabLayout.setupWithViewPager(viewPager);

                        for (int i = 0; i < result.size(); i++) {
                            tabLayout.getTabAt(i).setText(result.get(i).getDescription());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static final String TAG = "DetailsActivity";

    private void initView() {
        ArrayList<HomeBean.BodyBean.ResultBean> list = (ArrayList<HomeBean.BodyBean.ResultBean>) getIntent().getSerializableExtra("list");

        int position = getIntent().getIntExtra("position", 0);
        Glide.with(this).load(list.get(position).getTeacherPic()).into(image);
        name.setText(list.get(position).getTeacherName());
        title.setText(list.get(position).getTitle());
    }
}
