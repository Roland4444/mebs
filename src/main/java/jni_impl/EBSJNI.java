package jni_impl;

public class EBSJNI {
    static {
        System.loadLibrary("uk");
    }
    native int summ(int a, int b);
    native void init();
    native int checkFile(String filename);

}
