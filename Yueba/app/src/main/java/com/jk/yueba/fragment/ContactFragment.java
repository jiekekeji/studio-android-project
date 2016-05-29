package com.jk.yueba.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.jk.yueba.R;
import com.jk.yueba.activity.ChatActivity;
import com.jk.yueba.adapter.ContactsAdapter;
import com.jk.yueba.bean.User;
import com.jk.yueba.configure.SPUtils;
import com.jk.yueba.configure.SpKey;
import com.jk.yueba.net.URLapi;


import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by jack on 16/5/25.
 */
public class ContactFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private List<User> users;
    private ListView mListView;
    private ContactsAdapter mAdapter;

    @Override
    protected int getFragmentResLayout() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView() {
        users = new ArrayList<User>();
        mAdapter = new ContactsAdapter(users, activity);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        RequestParams params = new RequestParams();//请求参数
        params.addFormDataPart("owner_account", (String)SPUtils.get(activity, SpKey.ACCOUNT,""));
        LogUtils.i("请求好友列表结果:" + URLapi.CONTACTS);
        HttpRequest.post(URLapi.CONTACTS, params, new JsonHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                LogUtils.i("请求好友列表结果:" + jsonObject.toString());
                handleResult(jsonObject);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                LogUtils.e("onFailure:"+"errorCode="+errorCode+" msg"+msg);
            }
        });
    }

    private void handleResult(JSONObject jsonObject) {
        if (jsonObject.getInteger("code") == 2000) {
            users.addAll(JSON.parseArray(jsonObject.getJSONArray("users").toString(),
                    User.class));
            LogUtils.i("users="+users);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in;
        in = new Intent(activity, ChatActivity.class);
        in.putExtra("account",users.get(position).getAccount());
        startActivity(in);


    }
}
