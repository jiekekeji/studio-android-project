package com.jk.ndk001;

/**
 * Created by jack on 16/8/28.
 */
public class JniTools {

    public native String getName();

    static {
        System.loadLibrary("JniTools");
    }
}
