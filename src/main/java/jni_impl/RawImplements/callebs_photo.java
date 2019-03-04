package jni_impl.RawImplements;

import Table.TablesEBSCheck;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

public class callebs_photo {



    public TablesEBSCheck tebs = new TablesEBSCheck();
    public Map<Integer, String> onLoadLibraryErrors;
    public callebs_photo(TablesEBSCheck inp){
        this.tebs = inp;
        onLoadLibraryErrors = tebs.onLoadLibraryErrors;
    }
    public interface CLibrary extends Library {
        public static class ResultCheck extends Structure {
            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[] { "checkResult", "lastErrorInSession","ResultLoadingSoSymbols" });

            }
            public static class ByReference extends ResultCheck implements Structure.ByReference {}
            public int checkResult;
            public int lastErrorInSession;
            public int ResultLoadingSoSymbols;

        }


        CLibrary INSTANCE = (CLibrary) Native.loadLibrary(("sek"), CLibrary.class);
        ResultCheck lets_check(String config, String filename);

    }
    public CLibrary.ResultCheck call_ebs_photo(String config, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream("flush");
        fos.write("00".getBytes());
        fos.close();
        return CLibrary.INSTANCE.lets_check( config,  filename);
    }


}

