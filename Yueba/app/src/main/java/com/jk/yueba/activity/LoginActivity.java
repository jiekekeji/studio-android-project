package com.jk.yueba.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.jk.yueba.R;
import com.jk.yueba.chat.HunXin;
import com.jk.yueba.configure.SPUtils;
import com.jk.yueba.configure.SpKey;
import com.jk.yueba.widget.ProgressDialog;

/**
 * Created by jack on 16/5/25.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText accountTv;
    private EditText pswdTv;
    private Button loginBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initTilteView("登陆", true, "注册", mOptionTvOnclickListener);
        initView();

    }

    /**
     * 点击注册 打开注册页面
     */
    OptionTvOnclickListener mOptionTvOnclickListener = new OptionTvOnclickListener() {
        @Override
        public void optionTvOnclick() {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    };

    private void initView() {
        accountTv = (EditText) findViewById(R.id.account_et);
        pswdTv = (EditText) findViewById(R.id.pswd_et);
        loginBtn = (Button) findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                goToLogin();
                break;
        }
    }

    private void goToLogin() {
        final String account = accountTv.getText().toString();
        final String pswd = pswdTv.getText().toString();
        if (TextUtils.isEmpty(pswd) || TextUtils.isEmpty(account)) {
            //账号或密码没填写


            return;
        }

        ProgressDialog.show(this);
        HunXin.login(account, pswd, new EMCallBack() {
            @Override
            public void onSuccess() {
                LogUtils.i("onSuccess");
                ProgressDialog.close();
                //进入主页
                enterMainActivity();
                saveLoginInfo(account, pswd);

            }

            @Override
            public void onError(int error, String s) {
                ProgressDialog.close();
                LogUtils.i("onError:" + "error=" + error + " msg=" + s);
            }

            @Override
            public void onProgress(int progress, String s) {
                LogUtils.i("onProgress:" + "progress=" + progress + " msg=" + s);
            }
        });

    }

    /**
     * 保存登陆的账号和密码信息
     *
     * @param account
     * @param pswd
     */
    private void saveLoginInfo(String account, String pswd) {
        SPUtils.put(this,SpKey.ACCOUNT,account);
        SPUtils.put(this,SpKey.PSWD,pswd);
    }

    /**
     * 进入主页
     */
    private void enterMainActivity() {
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }
}
