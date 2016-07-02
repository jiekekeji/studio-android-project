package com.jk.srj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(final Subscriber<? super String> sub) {

                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            /**
                             * 在这里干点事情，
                             * 弄点东西出来
                             */
                            while (true){
                                sub.onNext("Hello, world!");

                            }
                        }
                    }.start();


                }
            }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            //接收myObservable发送的消息
            Log.i(TAG, "onNext:" + s);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable.subscribe(mySubscriber);
    }


}
