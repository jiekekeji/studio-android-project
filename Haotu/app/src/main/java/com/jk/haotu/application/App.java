package com.jk.haotu.application;

import android.app.Application;


import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by jack on 16/5/21.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initOkHttpFinal();
        initLogUtils();

    }

    /**
     * 初始化日志框架
     */
    private void initLogUtils() {
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("com.jk.haotu")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_INFO);
    }

    /**
     * 初始化网络工具OkHttpFinal
     */
    private void initOkHttpFinal() {
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
}
