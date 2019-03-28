package jna_impl.RawImplements;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    @Test
    void checkFile() throws IOException {
        callp.init();
        byte[] data = Files.readAllBytes(new File("/home/roland/Downloads/lol.wav").toPath());
        assertEquals(0, callp.checkFile(data));
    }
}