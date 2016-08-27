//
// Created by jack on 16/8/28.
//
#include "com_jk_ndk001_JniTools.h"
JNIEXPORT jstring JNICALL Java_com_jk_ndk001_JniTools_getName
  (JNIEnv *env, jobject  obj){
   return (*env)->NewStringUTF(env,"I from c");
  }