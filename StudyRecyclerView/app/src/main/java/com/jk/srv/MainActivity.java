package com.jk.srv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 纵向滚动
     *
     * @param v
     */
    public void btn1(View v) {
        Intent data = new Intent(this, TestActivity.class);
        data.putExtra("type", 0);
        startActivity(data);
    }

    /**
     * 横向滚动
     *
     * @param v
     */
    public void btn2(View v) {
        Intent data = new Intent(this, TestActivity.class);
        data.putExtra("type", 1);
        startActivity(data);
    }

    public void btn3(View v) {
        Intent data = new Intent(this, TestActivity.class);
        data.putExtra("type", 2);
        startActivity(data);
    }

    public void btn4(View v) {
        Intent data = new Intent(this, TestActivity.class);
        data.putExtra("type", 3);
        startActivity(data);
    }

    public void btn5(View v) {
        Intent data = new Intent(this, TestActivity.class);
        data.putExtra("type", 4);
        startActivity(data);
    }

    public void btn6(View v) {
        Intent data = new Intent(this, TestActivity.class);
        data.putExtra("type", 5);
        startActivity(data);
    }

    public void btn7(View v) {
    }

    public void btn8(View v) {
    }


}
