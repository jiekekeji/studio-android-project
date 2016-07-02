package com.jk.srj.ac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jk.srj.R;
import com.jk.srj.osb.Test1Subscribe;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    Observable<String> myObservable = Observable.create(new Test1Subscribe());

    Subscriber<String> mySubscriber = new Subscriber<String>() {

        @Override
        public void onStart() {
            super.onStart();
            Log.i(TAG, "onStart:" + "  thread:" + Thread.currentThread());
        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "onNext:" + s + "  thread:" + Thread.currentThread());
        }

        @Override
        public void onCompleted() {
            Log.i(TAG, "onCompleted:" + "  thread:" + Thread.currentThread());
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError" + e + "  thread:" + Thread.currentThread());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //同步订阅
        Subscription sub1 = myObservable.subscribe(mySubscriber);

        //异步订阅
        Subscription sub2 = myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mySubscriber);

    }


}
