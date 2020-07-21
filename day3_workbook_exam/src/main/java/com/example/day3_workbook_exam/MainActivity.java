package com.example.day3_workbook_exam;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private RecyclerView mRecycler;
    private NavigationView mNavigation;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mNavigation = (NavigationView) findViewById(R.id.navigation);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToolBar.setTitle("");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar, R.string.open, R.string.close);
        mDrawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
