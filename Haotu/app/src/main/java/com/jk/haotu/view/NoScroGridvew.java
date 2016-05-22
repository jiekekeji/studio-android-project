package com.jk.haotu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * gridview嵌套listview显示一行和滚动的问题
 * Created by jack on 16/5/22.
 */
public class NoScroGridvew extends GridView {

    public NoScroGridvew(Context context) {
        super(context);
    }

    public NoScroGridvew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScroGridvew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
