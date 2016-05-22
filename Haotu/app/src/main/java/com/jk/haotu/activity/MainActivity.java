package com.jk.haotu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jk.haotu.R;
import com.jk.haotu.adapter.MainActAdapter;
import com.jk.haotu.bean.Tngou;
import com.jk.haotu.net.URLapi;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;


public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener, View.OnClickListener {

    private PullToRefreshListView mListView;
    private MainActAdapter mAdapter;
    private List<Tngou> mList;
    private RelativeLayout mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        refreshData();
    }

    private void initView() {
        mTitleBar = (RelativeLayout) findViewById(R.id.titlebar);
        //默认titleBar设置为透明
        mTitleBar.getBackground().setAlpha(0);

        mListView = (PullToRefreshListView) findViewById(R.id.listview);
        mList = new ArrayList<Tngou>();
        mAdapter = new MainActAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);

        findViewById(R.id.classfiy_look).setOnClickListener(this);
    }

    private void refreshData() {
        RequestParams params = new RequestParams();//请求参数
        params.addFormDataPart("page", 1);
        params.addFormDataPart("rows", 5);
        params.addFormDataPart("id", "");
        HttpRequest.post(URLapi.IMG_LIST, params, new JsonHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                LogUtils.i("请求图片列表结果:" + jsonObject.toString());
                handleResult(jsonObject);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
            }
        });
    }

    /**
     * 处理网络请求的结果
     *
     * @param jsonObject
     */
    private void handleResult(JSONObject jsonObject) {
        if (null != jsonObject && jsonObject.getString("status").equals("true") && jsonObject.getJSONArray("tngou").size() > 0) {
            //请求成功并返回的结果有数据
            mList.addAll(JSON.parseArray(jsonObject.getJSONArray("tngou").toString(), Tngou.class));
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    /**
     * 这里主要实现头部titleBar的透明度变化
     *
     * @param absListView
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // 判断当前最上面显示的是不是头布局，因为PullToRefreshListView有刷新控件，
        //所以头布局的位置是1，即第二个
        if (firstVisibleItem == 1) {
            // 获取头布局
            View view = mListView.getChildAt(0);
            if (view != null) {
                // 获取头布局现在的最上部的位置的相反数
                int top = -view.getTop();
                // 获取头布局的高度
                int headerHeight = view.getHeight();
                // 满足这个条件的时候，是头布局在XListview的最上面第一个控件的时候，只有这个时候，我们才调整透明度
                if (top <= headerHeight && top >= 0) {
                    // 获取当前位置占头布局高度的百分比
                    float f = (float) top / (float) headerHeight;
                    mTitleBar.getBackground().setAlpha((int) (f * 255));
                    // 通知标题栏刷新显示
                    mTitleBar.invalidate();
                }
            }
        } else if (firstVisibleItem > 1) {
            mTitleBar.getBackground().setAlpha(255);
        } else {
            mTitleBar.getBackground().setAlpha(0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.classfiy_look:
                startActivity(new Intent(this, ImgClassFiyActivity.class));
                break;
        }
    }
}
