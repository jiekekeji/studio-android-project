package com.jk.yueba.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.EMCallBack;
import com.jk.yueba.R;
import com.jk.yueba.chat.HunXin;
import com.jk.yueba.fragment.ChatFragment;
import com.jk.yueba.fragment.ContactFragment;
import com.jk.yueba.fragment.FindFragment;
import com.jk.yueba.fragment.PersonFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FindFragment mFindFragment;
    private ChatFragment mChatFragment;
    private ContactFragment mContactFragment;
    private PersonFragment mPersonFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate=" + savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment(savedInstanceState);
        initView();
    }

    /**
     * 如果这东西由非正常状态恢复，那就不要了
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        if (null == savedInstanceState)
            return;
        FragmentManager fm = this.getSupportFragmentManager();
        mFindFragment = (FindFragment) fm.findFragmentByTag(FindFragment.class.getSimpleName());
        mChatFragment = (ChatFragment) fm.findFragmentByTag(ChatFragment.class.getSimpleName());
        mContactFragment = (ContactFragment) fm.findFragmentByTag(ContactFragment.class.getSimpleName());
        mPersonFragment = (PersonFragment) fm.findFragmentByTag(PersonFragment.class.getSimpleName());
        resetAllFragment();

    }

    private void initView() {
        ((RadioGroup) findViewById(R.id.tab_rg)).setOnCheckedChangeListener(this);

        //默认显示第一个Tab
        ((RadioButton) findViewById(R.id.tab1_rb)).setChecked(true);
        showCurrentTabFragment(R.id.tab1_rb);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        showCurrentTabFragment(checkedId);
    }

    /***
     * 根据选中的radiobutton显示相应的fragment
     *
     * @param checkedViewId
     */
    private void showCurrentTabFragment(int checkedViewId) {
        hideAllFragment();//在显示前，先隐藏所有
        switch (checkedViewId) {
            case R.id.tab1_rb:
                showFindFragment();
                break;
            case R.id.tab2_rb:
                showChatFragment();
                break;
            case R.id.tab3_rb:
                showContactFragment();
                break;
            case R.id.tab4_rb:
                showPersonFragment();
                break;
        }
    }

    /**
     * 显示FindFragment
     */
    private void showFindFragment() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        if (null == mFindFragment) {
            mFindFragment = new FindFragment();
            fts.add(R.id.container, mFindFragment, FindFragment.class.getSimpleName());
        } else {
            fts.show(mFindFragment);
        }
        fts.commit();
    }

    /**
     * 显示ChatFragment
     */
    private void showChatFragment() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        if (null == mChatFragment) {
            mChatFragment = new ChatFragment();
            fts.add(R.id.container, mChatFragment, ChatFragment.class.getSimpleName());
        } else {
            fts.show(mChatFragment);
        }
        fts.commit();
    }

    /**
     * 显示ContactFragment
     */
    private void showContactFragment() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        if (null == mContactFragment) {
            mContactFragment = new ContactFragment();
            fts.add(R.id.container, mContactFragment, ContactFragment.class.getSimpleName());
        } else {
            fts.show(mContactFragment);
        }
        fts.commit();
    }


    /**
     * 显示PersonFragment
     */
    private void showPersonFragment() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        if (null == mPersonFragment) {
            mPersonFragment = new PersonFragment();
            fts.add(R.id.container, mPersonFragment, PersonFragment.class.getSimpleName());
        } else {
            fts.show(mPersonFragment);
        }
        fts.commit();
    }

    /**
     * 隐藏所有的fragment
     */
    private void hideAllFragment() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        if (null != mFindFragment) {
            fts.hide(mFindFragment);
        }
        if (null != mChatFragment) {
            fts.hide(mChatFragment);
        }
        if (null != mContactFragment) {
            fts.hide(mContactFragment);
        }
        if (null != mPersonFragment) {
            fts.hide(mPersonFragment);
        }
        fts.commit();
    }

    /**
     * 重置所有的fragment
     */
    private void resetAllFragment() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        if (null != mFindFragment) {
            fts.remove(mFindFragment);
            mFindFragment = null;
        }
        if (null != mChatFragment) {
            fts.remove(mChatFragment);
            mChatFragment = null;
        }
        if (null != mContactFragment) {
            fts.remove(mContactFragment);
            mContactFragment = null;
        }
        if (null != mPersonFragment) {
            fts.remove(mPersonFragment);
            mPersonFragment = null;
        }
        fts.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HunXin.logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                LogUtils.i("退出登陆成功");
            }

            @Override
            public void onError(int i, String s) {
                LogUtils.i("退出登陆失败");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
