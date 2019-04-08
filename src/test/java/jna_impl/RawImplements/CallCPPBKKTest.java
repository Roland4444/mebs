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

    @Test
    void checkUints(){
        byte[] arr = new byte[]{0x00, 0x02};
        int uint8 = arr[0] & 0xFF;
        int int2 = arr[1];
        System.out.println("First =>"+uint8+"\nSecond =>"+ int2);
    }
}