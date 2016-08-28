package com.jk.view001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private View leftView;
    private View rightView;
    private LinearLayout ll;
    private RelativeLayout vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftView = findViewById(R.id.left_view);
        rightView = findViewById(R.id.right_view);
        ll= (LinearLayout) findViewById(R.id.ll);
        vp= (RelativeLayout) findViewById(R.id.vp);
    }

    //ScrollTo和ScrollBy是内容的的偏移，不是调用者的偏移
    /**
     * 相对原来的位置偏移，运行demo点点就知道
     */
    private int x1=0;
    private int y1=0;
    public void ScrollTo(View view) {
        x1=x1-4;
        y1=y1-4;
        leftView.scrollTo(x1, y1);
    }

    /**
     * 相对当前的位置偏移
     */
    private int x2=0;
    private int y2=0;
    public void ScrollBy(View view) {
        x2=x2-4;
        y2=y2-4;
        rightView.scrollBy(x2, y2);
    }

    private int x3=0;
    private int y3=0;
    public void llScroll(View view) {
        x3=x3-4;
        y3=y3-4;
        ll.scrollTo(x3,y3);
    }

    /**
     * 切换view
     */
    public void vp(View view){
        vp.scrollBy(740,0);
    }
}
