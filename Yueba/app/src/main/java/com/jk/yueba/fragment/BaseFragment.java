package com.jk.yueba.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk.yueba.R;


/**
 * Created by jack on 16/5/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected Activity activity;
    protected View rootView;
    protected LayoutInflater inflater;

    //start 页面头部公共的view
    protected ImageView backIv;
    protected TextView titleTv;
    protected TextView optionTv;
    //end 页面头部公共的view
    private OptionTvOnclickListener optionTvOnclickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(getFragmentResLayout(), null);
        }
        initView();
        initData();
        return rootView;
    }

    /**
     * 如果需要的话，初始化页面头部的view
     *
     * @param title              标题
     * @param isOptionTvVisitble 右侧空间是否显示
     */
    protected void initTilteView(String title, boolean isOptionTvVisitble,
                                 String optionTvText,
                                 OptionTvOnclickListener optionTvOnclickListener) {
        backIv = (ImageView) findViewById(R.id.back_iv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        optionTv = (TextView) findViewById(R.id.option_tv);

        titleTv.setText(title);
        if (isOptionTvVisitble)
            optionTv.setVisibility(View.VISIBLE);
        else
            optionTv.setVisibility(View.GONE);
        optionTv.setText(optionTvText);

        optionTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                activity.finish();
                break;
            case R.id.option_tv:
                if (null != optionTvOnclickListener)
                    optionTvOnclickListener.optionTvOnclick();
                break;
        }
    }

    /**
     * titlbar右边的view被点击的监听者
     */
    public interface OptionTvOnclickListener {
        public void optionTvOnclick();
    }

    /**
     * 初始化页面头部的view
     */
    protected void initTilteView() {
        backIv = (ImageView) findViewById(R.id.back_iv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        optionTv = (TextView) findViewById(R.id.option_tv);

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * 获取布局文件下的子View
     *
     * @param resId
     * @return
     */
    protected View findViewById(int resId) {
        return rootView.findViewById(resId);
    }

    /**
     * 设置页面布局文件
     *
     * @return
     */
    protected abstract int getFragmentResLayout();

    protected abstract void initView();

    protected abstract void initData();


}
