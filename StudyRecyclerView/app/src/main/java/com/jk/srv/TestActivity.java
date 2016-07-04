package com.jk.srv;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import com.jk.srv.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-07-04.
 */
public class TestActivity extends FragmentActivity {

    private RecyclerView mRecyclerView;
    public List<String> datas = null;
    private MyAdapter mAdapter;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        initDatas();
        initViews();

    }

    /**
     * 数据准备
     */
    private void initDatas() {
        type = getIntent().getIntExtra("type", 0);
        datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(i + " item");
        }
    }

    /**
     * view准备
     */
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        switch (type) {
            case 0:
                LinearLayoutManager mLayoutManager0 = new LinearLayoutManager(this);
                mLayoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager0);
                break;
            case 1:
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
                mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(mLayoutManager1);
                break;
            case 2:
                //3列
                GridLayoutManager mLayoutManager2 = new GridLayoutManager(this, 3);
                mLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager2);
                break;
            case 3:
                //3列
                GridLayoutManager mLayoutManager3 = new GridLayoutManager(this, 3);
                mLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(mLayoutManager3);
                break;
            case 4:
                //2列
                StaggeredGridLayoutManager mLayoutManager4 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager4);
                break;
            case 5:
                //2列
                StaggeredGridLayoutManager mLayoutManager5 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(mLayoutManager5);
                break;

        }
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        mAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
    }
}
