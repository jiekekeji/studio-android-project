package com.jk.srj.osb;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016-07-02.
 */
public class Test2Subscribe implements Observable.OnSubscribe<String> {

    @Override
    public void call(Subscriber<? super String> subscriber) {

        subscriber.onStart();

        new Thread() {
            @Override
            public void run() {
                super.run();

            }
        }.start();

    }
}
