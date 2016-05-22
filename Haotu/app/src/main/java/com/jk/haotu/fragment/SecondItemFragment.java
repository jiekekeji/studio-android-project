package com.jk.haotu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.jk.haotu.R;
import com.jk.haotu.adapter.FisrtItemFrgAdapter;
import com.jk.haotu.adapter.SecondItemFrgAdapter;
import com.jk.haotu.bean.ImgClassify;
import com.jk.haotu.bean.Tngou;
import com.jk.haotu.net.URLapi;
import com.jk.haotu.view.NoScroGridvew;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 首页头部
 * Created by jack on 16/5/21.
 */
public class SecondItemFragment extends BaseFragment {

    private View mRootView;
    private NoScroGridvew mScroGridView;
    private SecondItemFrgAdapter mAdapter;
    private List<ImgClassify> mClassifies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(R.layout.fr_second_item, null);
        }

        initView();
        initData();
        return mRootView;
    }

    private void initData() {
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

    private void initView() {
        mScroGridView = (NoScroGridvew) mRootView.findViewById(R.id.noscrogridvew);
        mClassifies = new ArrayList<ImgClassify>();
        mAdapter = new SecondItemFrgAdapter(activity, mClassifies);
        mScroGridView.setAdapter(mAdapter);
    }

    /**
     * 处理网络请求的结果
     *
     * @param jsonObject
     */
    private void handleResult(JSONObject jsonObject) {
        if (null != jsonObject && jsonObject.getString("status").equals("true") && jsonObject.getJSONArray("tngou").size() > 0) {
            //请求成功并返回的结果又数据
            mClassifies.addAll(JSON.parseArray(jsonObject.getJSONArray("tngou").toString(), ImgClassify.class));
            mAdapter.notifyDataSetChanged();
        }
    }


}
