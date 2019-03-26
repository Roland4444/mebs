package jni_impl;

public class EBSJNI {
    static {
        System.loadLibrary("native");
    }
    native int summ(int a, int b);
    native void init();
    native int checkFile(String filename);

}
