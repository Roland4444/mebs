package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class callPTest {
    callP callp = new callP();

    @Test
    void init() {
        callp.init();
    }

    @Test
    void call() {
        callp.init();
        for (int i=0; i<15; i++)
            callp.call();
    }
}