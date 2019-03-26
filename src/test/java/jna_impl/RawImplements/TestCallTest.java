package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCallTest {

    @Test
    void ret0() {
        var tc = new TestCall();
        assertEquals(0, tc.ret0());
        assertEquals(1, tc.ret1());
        assertEquals(-1, tc.ret_1());
        assertEquals(-3, tc.ret_3());
    }
}