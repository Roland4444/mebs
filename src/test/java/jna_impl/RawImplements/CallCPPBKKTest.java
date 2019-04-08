package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallCPPBKKTest {

    @Test
    void check() {
        var t = new CallCPPBKK();
        t.init();
        assertEquals(0, t.check("./lol2.wav"));
    }

}