package com.example.day2_workbook_exam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.fragment.CollectionFragment;
import com.example.fragment.NewsFragment;
import com.example.fragment.TestFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new CollectionFragment());
        fragments.add(new TestFragment());

        final ArrayList<String> strings = new ArrayList<>();
        strings.add("我的");
        strings.add("收藏");
        strings.add("测试");

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
