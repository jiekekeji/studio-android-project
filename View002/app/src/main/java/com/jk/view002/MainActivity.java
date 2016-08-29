package com.jk.view002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    private View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = findViewById(R.id.moveView);
    }

    int x = 0;
    int y = 0;

    public void move(View view) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view1.getLayoutParams();
        x = x + 5;
        y = y + 5;
//        params.leftMargin = x;
//        params.topMargin = y;
        params.setMargins(x, y, 0, 0);
        view1.requestLayout();

    }
}
