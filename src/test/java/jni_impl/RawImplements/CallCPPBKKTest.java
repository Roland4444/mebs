package jni_impl.RawImplements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallCPPBKKTest {
    CallCPPBKK t = new CallCPPBKK();
    @Test
    void callsumm() {

        assertEquals(6, t.callsumm(2,4));
    }

    @Test
    void check() {
        t.init();
        assertEquals(0, t.check("/home/roland/Downloads/lol.wav"));
    }
}