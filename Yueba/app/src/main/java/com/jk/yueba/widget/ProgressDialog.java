package com.jk.yueba.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.yueba.R;

/**
 * Created by jack on 16/5/29.
 */
public class ProgressDialog {

    private static Dialog mDialog;
    private static Animation animation;
    private static ImageView mImageView;

    public static void createLoadingDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_progress_bar, null);// 得到加载view
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.container);// 加载布局
        // main.xml中的ImageView
        mImageView = (ImageView) v.findViewById(R.id.imageView);
        // 加载动画
        animation = AnimationUtils.loadAnimation(
                context, R.anim.am_dialog_loading);

        mDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mImageView.clearAnimation();
            }
        });
        mDialog.setCancelable(false);// 不可以用“返回键”取消
        mDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
    }

    /**
     * 显示对话框
     *
     * @param context
     */
    public static void show(Context context) {
        if (null == mDialog) {
            createLoadingDialog(context);
        }
        if (mDialog.isShowing())
            return;
        mDialog.show();
        mImageView.startAnimation(animation);
    }

    /**
     * 关闭正在显示的对话框
     */
    public static void close() {
        if (null == mDialog)
            return;
        if (mDialog.isShowing())
            mDialog.dismiss();
    }
}
