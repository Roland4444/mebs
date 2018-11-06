package jni_impl.RawImplements;

import essens.TablesEBSCheck;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class callebs_photoTest {
    callebs_photo cebs = new callebs_photo(new TablesEBSCheck());

    @Test
    public void call_ebs() throws InterruptedException, IOException {

        var rc = cebs.call_ebs_photo("./ci_configuration.json", "/home/roland/Downloads/.build_l64/tests_data/photo/photo-9fN3XP0T.jpg");

        assertEquals(rc.lastErrorInSession, 0);

    }

    void printEBS(callebs_photo.CLibrary.ResultCheck rc) {
        System.out.println("\n\nPROC RET    " + rc.lastErrorInSession);
        System.out.println("CHECK  =    " + rc.checkResult);
        System.out.println("INCALL      " + rc.ResultLoadingSoSymbols + "\n\n");
        System.out.println("Resulting loading so =>" + cebs.onLoadLibraryErrors.get(rc.ResultLoadingSoSymbols));

    }

    @Test
    public void call_ebs1() {
        callEBS_sound ebs = new callEBS_sound(new TablesEBSCheck());
        assertNotEquals(null, ebs);
    }
}