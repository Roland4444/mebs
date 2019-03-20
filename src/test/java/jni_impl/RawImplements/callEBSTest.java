package jni_impl.RawImplements;

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
}