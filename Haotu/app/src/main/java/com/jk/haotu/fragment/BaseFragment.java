package com.jk.haotu.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by jack on 16/5/21.
 */
public class BaseFragment extends Fragment {

    protected Activity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity= (Activity) context;
    }
}
