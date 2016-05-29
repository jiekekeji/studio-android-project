package com.jk.yueba.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.chat.EMClient;
import com.jk.yueba.R;
import com.jk.yueba.configure.SPUtils;
import com.jk.yueba.configure.SpKey;

/**
 * Created by jack on 16/5/25.
 */
public class LauncherActivity extends BaseActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        isLoginAuto();
    }

    private void isLoginAuto() {
        //判断是否有登陆的账号密码信息
//        String account = (String) SPUtils.get(this, SpKey.ACCOUNT, "");
//        String pswd = (String) SPUtils.get(this, SpKey.PSWD, "");
//        LogUtils.i("account=" + account + "\n" + "pswd=" + pswd);
//        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pswd)) {
//            //到登陆页面
//            startActivity(new Intent(this, LoginActivity.class));
//            return;
//        }
        startActivity(new Intent(this, LoginActivity.class));
        //自动登陆
    }
}
