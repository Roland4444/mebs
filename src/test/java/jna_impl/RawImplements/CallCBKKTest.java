package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallCBKKTest {

    @Test
    void check() {
        var t = new CallC_BKK();
        t.init();
        assertEquals(0, t.check("./lol2.wav"));
    }

    @Test
    void getStr() {
        var f = new CallC_BKK();
        assertEquals("Example", f.getStr());
    }
}