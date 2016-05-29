package com.jk.yueba.chat;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jack on 16/5/18.
 */
public class HunXin {

    private static final String TAG = HunXin.class.getSimpleName();

    //*****初始配置  start *********//

    /**
     * 初始化SDK
     */
    public static void initSDK(Context mContext) {

        //1、判断SDK是否已初始化
        if (isSDKRunning(mContext)) {
            return;
        }

        //2、初始化SDK

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(false);//自动登陆否
        //初始化
        EMClient.getInstance().init(mContext, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

    }

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param password 密码
     * @param callBack 登陆状态的监听者
     */
    public static void login(String userName, String password, EMCallBack callBack) {
        EMClient.getInstance().login(userName, password, callBack);
    }

    /**
     * 退出登陆
     *
     * @param b        true 表示退出登录时解绑 GCM 或者小米推送的 token
     * @param callBack 退出状态的监听
     */
    public static void logout(boolean b, EMCallBack callBack) {
        EMClient.getInstance().logout(b, callBack);
    }

    /**
     * 注册链接的监听者
     * 实现对网络，是否在异地登陆，账号状态的监听
     *
     * @param listener
     */
    public static void registerConnectionListener(EMConnectionListener listener) {
        EMClient.getInstance().addConnectionListener(listener);

    }

    /**
     * 移除链接的监听者
     *
     * @param listener
     */
    public static void removeConnectionListener(EMConnectionListener listener) {
        EMClient.getInstance().removeConnectionListener(listener);

    }

    /**
     * 加载群组和会话列表
     */
    public static void getGroupAndConversation() {
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
    }

    /**
     * 判断SDK是否已经在运行
     *
     * @return 已经在运行返回true, else false。
     */
    private static boolean isSDKRunning(Context mContext) {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid, mContext);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
            Log.e(TAG, "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return true;
        }

        return false;
    }

    /**
     * 获取应用运行的名称
     *
     * @param pID      应用Pid
     * @param mContext
     * @return nil or 应用名称
     */
    private static String getAppName(int pID, Context mContext) {
        String processName = null;
        ActivityManager am = (ActivityManager) mContext.getSystemService(Activity.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = mContext.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.d("Process", "Error>> :" + e.toString());
            }
        }
        return processName;
    }


    //*****初始配置  end *********//


    //*****消息发送相关  start *********//


    /**
     * 发送文本消息
     *
     * @param content        发送的内容
     * @param toChatUsername 消息接收者userName，或群ID
     * @param isGroupChat    是否是群聊 true为群聊,else 单聊
     * @param callBack       消息发送状态的监听
     */
    public static void sendTextMsg(String content, String toChatUsername, boolean isGroupChat, EMCallBack callBack) {

        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        //监听消息发送的状态
        if (null != callBack) {
            message.setMessageStatusCallback(callBack);
        }
        //如果是群聊，设置chattype，默认是单聊
        if (isGroupChat)
            message.setChatType(EMMessage.ChatType.GroupChat);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送语音消息
     *
     * @param filePath       语音文件路径
     * @param length         语音的时常，单位秒
     * @param toChatUsername 是否是群聊 true为群聊,else 单聊
     * @param callBack       消息发送状态的监听
     */
    public static void sendVoiceMsg(String filePath, int length, String toChatUsername, boolean isGroupChat, EMCallBack callBack) {
        //filePath为语音文件路径，length为录音时间(秒)
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
        //监听消息发送的状态
        if (null != callBack) {
            message.setMessageStatusCallback(callBack);
        }
        //如果是群聊，设置chattype，默认是单聊
        if (isGroupChat)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }


    /**
     * 发送视频消息
     *
     * @param filePath       视频文件路径
     * @param thumbPath      thumbPath为视频预览图路径
     * @param length         视频的时常，单位秒
     * @param toChatUsername 是否是群聊 true为群聊,else 单聊
     * @param callBack       消息发送状态的监听
     */
    public static void sendVideoMsg(String filePath, String thumbPath, int length, String toChatUsername, boolean isGroupChat, EMCallBack callBack) {
        //videoPath为视频本地路径，thumbPath为视频预览图路径，videoLength为视频时间长度
        EMMessage message = EMMessage.createVideoSendMessage(filePath, thumbPath, length, toChatUsername);
        //监听消息发送的状态
        if (null != callBack) {
            message.setMessageStatusCallback(callBack);
        }
        //如果是群聊，设置chattype，默认是单聊
        if (isGroupChat)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }


    //*****消息发送相关  end *********//
}
