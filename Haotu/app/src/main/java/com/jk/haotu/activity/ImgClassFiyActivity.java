package com.jk.haotu.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jk.haotu.R;
import com.jk.haotu.adapter.ImgClassfiyAdapter;
import com.jk.haotu.adapter.ImgClassfiyDetailAdapter;
import com.jk.haotu.adapter.MainActAdapter;
import com.jk.haotu.bean.ImgClassify;
import com.jk.haotu.bean.Tngou;
import com.jk.haotu.net.URLapi;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 分类查看
 * Created by jack on 16/5/22.
 */
public class ImgClassFiyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private PullToRefreshListView mClassfiyListView;
    private PullToRefreshListView mClassfiyDetailListView;

    private ImgClassfiyAdapter mImgClassfiyAdapter;
    private ImgClassfiyDetailAdapter mImgClassfiyDetailAdapter;

    private List<Tngou> mTngous;
    private List<ImgClassify> mClassifies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgclassfiy);
        initView();
        refreshData();
    }

    private void initView() {
        mClassfiyListView = (PullToRefreshListView) findViewById(R.id.classfiy);
        mClassfiyListView.setOnItemClickListener(this);
        mClassfiyDetailListView = (PullToRefreshListView) findViewById(R.id.classfiy_detail);

        //分类详情
        mTngous = new ArrayList<Tngou>();
        mImgClassfiyDetailAdapter = new ImgClassfiyDetailAdapter(this, mTngous);
        mClassfiyDetailListView.setAdapter(mImgClassfiyDetailAdapter);

        //分类
        mClassifies = new ArrayList<ImgClassify>();
        mImgClassfiyAdapter = new ImgClassfiyAdapter(this, mClassifies);
        mClassfiyListView.setAdapter(mImgClassfiyAdapter);

    }

    private void refreshData() {
        HttpRequest.get(URLapi.IMG_CLASSFIES, new JsonHttpRequestCallback() {
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

    //*******start 先获取分类列表，之后再根据分类的第一个分类初始化分类详情********//

    /**
     * 处理网络请求的结果
     *
     * @param jsonObject
     */
    private void handleResult(JSONObject jsonObject) {
        if (null != jsonObject && jsonObject.getString("status").equals("true") && jsonObject.getJSONArray("tngou").size() > 0) {
            //请求成功并返回的结果又数据
            mClassifies.addAll(JSON.parseArray(jsonObject.getJSONArray("tngou").toString(), ImgClassify.class));
            mImgClassfiyAdapter.notifyDataSetChanged();
            initClassfiyDetail(mClassifies.get(0).getId());
        }
    }

    /**
     * 初始化分类详情的数据
     */
    private void initClassfiyDetail(String classId) {
        RequestParams params = new RequestParams();//请求参数
        params.addFormDataPart("page", 1);
        params.addFormDataPart("rows", 5);
        params.addFormDataPart("id", classId);
        HttpRequest.post(URLapi.IMG_LIST, params, new JsonHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                LogUtils.i("请求图片列表结果:" + jsonObject.toString());
                handleClassfiyDetailResult(jsonObject);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
            }
        });

    }

    private void handleClassfiyDetailResult(JSONObject jsonObject) {
        if (null != jsonObject && jsonObject.getString("status").equals("true") && jsonObject.getJSONArray("tngou").size() > 0) {
            //请求成功并返回的结果有数据
            mTngous.clear();
            mTngous.addAll(JSON.parseArray(jsonObject.getJSONArray("tngou").toString(), Tngou.class));
            mImgClassfiyDetailAdapter.notifyDataSetChanged();
        }
    }

    //*******end 先获取分类列表，之后再根据分类的第一个分类初始化分类详情********//

    //*******start 根据点击不同的分类显示分类详情********//
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //刷新分类列表的选中状态
        mImgClassfiyAdapter.setSelectPosition(i-1);

        //刷新分类详情
        initClassfiyDetail(mClassifies.get(i-1).getId());
    }

    //*******end 根据点击不同的分类显示分类详情********//
}
