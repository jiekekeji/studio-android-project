package com.jk.srj.ac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jk.srj.R;
import com.jk.srj.osb.Test1Subscribe;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*****
     * start   test1
     *****/

    //mObservable1
    Observable<String> mObservable1 = Observable.create(new Test1Subscribe());

    Subscription sub1;

    Subscription sub2;
    //mSubscriber1
    Subscriber<String> mSubscriber1 = new Subscriber<String>() {

        @Override
        public void onStart() {
            super.onStart();
            Log.i(TAG, "onStart:" + "  thread:" + Thread.currentThread().getId());
        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "onNext:" + s + "  thread:" + Thread.currentThread().getId());
        }

        @Override
        public void onCompleted() {
            Log.i(TAG, "onCompleted:" + "  thread:" + Thread.currentThread().getId());
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError" + e + "  thread:" + Thread.currentThread().getId());
        }
    };

    public void btn1(View view) {
        if (null != sub1 && sub1.isUnsubscribed())
            sub1.unsubscribe();
        sub1 = mObservable1.subscribe(mSubscriber1);
    }

    public void btn2(View view) {
        if (null != sub2 && sub2.isUnsubscribed()) {
            sub2.unsubscribe();
        }
        sub2 = mObservable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber1);
    }

    /***** end   test1   *****/

    /*****
     * start test2
     ****/
    String[] names = {"1", "2", "3", "4"};

    public void btn3(View view) {
        /**
         * 相当于 subscriber.onNext（）
         * 被多次调用
         */
        Observable.from(names)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber1);
    }

    public void btn4(View view) {
        Observable.just("1", "2", "3", "4")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber1);
    }

    /***** end test2 *****/


    /*****
     * start 只关心onNext
     *****/
    public void btn5(View view) {
        if (null != sub2 && sub2.isUnsubscribed()) {
            sub2.unsubscribe();
        }
        sub2 = mObservable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "Action1=" + s);
                    }
                });
    }

    /***** end 只关心onNext *****/

    /*****
     * start 变换
     *****/
    public void btn6(View view) {
        Observable.just("1", "2", "3", "4").map(new Func1<String, String>() {
            //Func1第一个参数为传入的数据类型，第二个是返回的数据类型
            @Override
            public String call(String s) {
                Log.i(TAG, "map call Thread=" + Thread.currentThread().getId());
                //在字符串后面再加 &123
                return s + "&123";
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber1);
    }

    /***** end 变换 *****/

    /******
     * start 变换
     *****/
    public void btn7(View view) {
        Observable.just("1", "2", "3", "4").flatMap(new Func1<String, Observable<String>>() {
            //Func1第一个参数为传入的数据类型，第二个是返回的数据类型
            @Override
            public Observable call(String s) {
                Log.i(TAG, "map call Thread=" + Thread.currentThread().getId());
                Log.i(TAG, "map call Thread=" + s);
                String[] ss = {"12", "34", s};
                return Observable.from(ss);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber1);
    }
    /***** end 变换 *****/
}
