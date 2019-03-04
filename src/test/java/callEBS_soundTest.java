
import Table.TablesEBSCheck;
import jni_impl.RawImplements.callEBS_sound;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class callEBS_soundTest {
    callEBS_sound cebs = new callEBS_sound(new TablesEBSCheck());
    @Test
    public void call_ebs() throws InterruptedException, IOException {

        callEBS_sound.CLibrary.ResultCheck rc = new callEBS_sound.CLibrary.ResultCheck();
        rc = cebs.call_ebs("./cv_configuration.json", "./5.wav");

        assertEquals(rc.lastErrorInSession, 0);
        printEBS(callEBS_sound.CLibrary.INSTANCE.lets_check("./cv_configuration.json", "./5.wav"));
        printEBS(callEBS_sound.CLibrary.INSTANCE.lets_check("./cv_configuration.json", "./a2002011001_e02_16kHz.wav.wav"));
        printEBS(callEBS_sound.CLibrary.INSTANCE.lets_check("./cv_configuration.json000", "./5.wav"));
    }

    void printEBS(callEBS_sound.CLibrary.ResultCheck rc){
        System.out.println("\n\nPROC RET    "+rc.lastErrorInSession);
        System.out.println("CHECK  =    "+rc.checkResult);
        System.out.println("INCALL      "+rc.ResultLoadingSoSymbols+"\n\n");
        System.out.println("Resulting loading so =>"+cebs.onLoadLibraryErrors.get(rc.ResultLoadingSoSymbols));

    }

    @Test
    public void call_ebs1() {
        callEBS_sound ebs = new callEBS_sound(new TablesEBSCheck());
        assertNotEquals(null, ebs);
    }


}