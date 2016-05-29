package com.jk.yueba.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk.yueba.R;


/**
 * Created by jack on 16/5/25.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    //start 页面头部公共的view
    protected ImageView backIv;
    protected TextView titleTv;
    protected TextView optionTv;
    //end 页面头部公共的view

    private OptionTvOnclickListener optionTvOnclickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                finish();
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
}
