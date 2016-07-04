package com.jk.srj.osb;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016-07-02.
 */
public class Test1Subscribe implements Observable.OnSubscribe<String> {

    private static final String TAG = Test1Subscribe.class.getSimpleName();

    @Override
    public void call(Subscriber<? super String> subscriber) {
        Log.i(TAG, "call thread:" + Thread.currentThread().getId());
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subscriber.onNext("111111111111");
        subscriber.onNext("222222222222");
        subscriber.onNext("333333333333");
        subscriber.onNext("444444444444");
        subscriber.onNext("555555555555");
    }
}
