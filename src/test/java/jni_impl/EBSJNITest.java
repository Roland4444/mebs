package jni_impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EBSJNITest {

    @Test
    void summ() {
        var jni = new EBSJNI();
        assertEquals(3, jni.summ(2,1));
        jni.init();
        assertEquals(0, jni.checkFile("/home/roland/Downloads/lol.wav"));
    }
}