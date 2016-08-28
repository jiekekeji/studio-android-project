package com.jk.ndk002;

/**
 * Created by jack on 16/8/27.
 */
public class MyJavaMethed {

    /**
     * 供c调用的静态方法
     *
     * @return
     */
    public static String javaStaticMethed() {
        return "I am come from javaStaticMethed";
    }

    /**
     * 供c调用的实例方法
     *
     * @return
     */
    public String javaInstanceMethed() {
        return "I am come from javaInstanceMethed";
    }

}
