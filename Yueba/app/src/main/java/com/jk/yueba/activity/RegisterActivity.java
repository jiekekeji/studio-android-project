package com.jk.yueba.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.jk.yueba.R;
import com.jk.yueba.configure.SPUtils;
import com.jk.yueba.configure.SpKey;

/**
 * 注册页面
 * Created by jack on 16/5/25.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initTilteView("登陆", true, "注册", mOptionTvOnclickListener);
        isLoginAuto();
    }

    /**
     * 点击注册 打开注册页面
     */
    OptionTvOnclickListener mOptionTvOnclickListener = new OptionTvOnclickListener() {
        @Override
        public void optionTvOnclick() {
//            startActivity();
        }
    };

    private void isLoginAuto() {
        //判断是否有登陆的账号密码信息
        String account = (String) SPUtils.get(this, SpKey.ACCOUNT, "");
        String pswd = (String) SPUtils.get(this, SpKey.PSWD, "");
        LogUtils.i("account=" + account + "\n" + "pswd" + pswd);
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pswd)) {
            //到登陆页面
            return;
        }

        //自动登陆
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option_tv:

                break;
        }
    }
}
