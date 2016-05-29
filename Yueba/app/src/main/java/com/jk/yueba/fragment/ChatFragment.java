package com.jk.yueba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.jk.yueba.R;

import java.util.Map;

/**
 * Created by jack on 16/5/25.
 */
public class ChatFragment extends BaseFragment {

    @Override
    protected int getFragmentResLayout() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //获取会话列表
        Map<String, EMConversation> map = EMClient.getInstance()
                .chatManager().getAllConversations();
        LogUtils.i("会话列表=" + map);
    }
}
