package com.jk.ndk002;

/**
 * Created by jack on 16/8/28.
 */
public class JniTools {

    /**
     * c调用java的静态方法
     * @return
     */
    public native  String callJavaStaticMethod();

    /**
     * c调用java的实例方法
     * @return
     */
    public static native String callJavaInstaceMethod();

    static {
        System.loadLibrary("JniTools");
    }
}
