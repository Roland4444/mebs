package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class callEBSTest {

    @Test
    void checkfile() throws IOException {
        var checker = new callEBS();
        checker.init();
        checker.foreach("input");
    }

    @Test
    void lets_check() {
        var checker = new callEBS();
        assertEquals(0, checker.lets_check("/home/roland/Downloads/lol.wav"));
    }
}