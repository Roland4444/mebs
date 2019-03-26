package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SCallTest {

    @Test
    void get() {
        var SCall = new SCall();
        assertEquals(5, SCall.get());
    }
}