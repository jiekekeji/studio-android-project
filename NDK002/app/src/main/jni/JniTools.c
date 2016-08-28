#include "com_jk_ndk002_JniTools.h"
#include <stdio.h>
/**
 * 调用java的静态方法
 */
JNIEXPORT jstring JNICALL Java_com_jk_ndk002_JniTools_callJavaStaticMethod
        (JNIEnv *env, jobject obj) {
    // 调用MyJavaMethed的静态方法getString
    //找到那个类
    jclass clazz = (*env)->FindClass(env, "com/jk/ndk002/MyJavaMethed");
    if (NULL == clazz) {
        printf("can't find class MyJavaMethed");
        return (*env)->NewStringUTF(env, "can't find class MyJavaMethed");
    }
    //找到那个方法
    jmethodID getString = (*env)->GetStaticMethodID(env, clazz, "javaStaticMethed",
                                                    "()Ljava/lang/String;");
    if (NULL == getString) {
        (*env)->DeleteLocalRef(env, clazz);
        printf("can't find method javaStaticMethed from MyJavaMethed ");
        return (*env)->NewStringUTF(env, "can't find method javaStaticMethed from MyJavaMethed");
    }
    //调用方法
    jstring result = (*env)->CallStaticObjectMethod(env, clazz, getString);
    const char *resultChar = (*env)->GetStringUTFChars(env, result, NULL);
    //删除局部引用
    (*env)->DeleteLocalRef(env, clazz);
    (*env)->DeleteLocalRef(env, result);
    return (*env)->NewStringUTF(env, resultChar);
}
/**
 * 调用java的实例方法
 */
JNIEXPORT jstring JNICALL Java_com_jk_ndk002_JniTools_callJavaInstaceMethod
        (JNIEnv *env, jclass cls) {
    // 1、找到MyJavaMethed类
    jclass clazz = (*env)->FindClass(env, "com/jk/ndk002/MyJavaMethed");
    if (clazz == NULL) {
        return (*env)->NewStringUTF(env, "can't find class MyJavaMethed");
    }
    // 2、获取类的默认构造方法ID
    jmethodID construct = (*env)->GetMethodID(env, clazz, "<init>", "()V");
    if (construct == NULL) {
        return (*env)->NewStringUTF(env, "can't find construct methed");
    }
    // 3、查找实例方法的ID
    jmethodID javaInstanceMethed = (*env)->GetMethodID(env, clazz, "javaInstanceMethed",
                                                       "()Ljava/lang/String;");
    if (javaInstanceMethed == NULL) {
        return (*env)->NewStringUTF(env, "can't find method javaInstanceMethed");
    }
    // 4、创建该类的实例
    jobject jobj = (*env)->NewObject(env, clazz, construct);
    if (jobj == NULL) {
        return (*env)->NewStringUTF(env, "init jobj fail");
    }
    // 5、调用对象的实例方法
    jstring result = (jstring) (*env)->CallObjectMethod(env, jobj, javaInstanceMethed);
    const char *resultChar = (*env)->GetStringUTFChars(env, result, NULL);
    // 删除局部引用
    (*env)->DeleteLocalRef(env, clazz);
    (*env)->DeleteLocalRef(env, jobj);
    (*env)->DeleteLocalRef(env, result);
    return (*env)->NewStringUTF(env, resultChar);

}