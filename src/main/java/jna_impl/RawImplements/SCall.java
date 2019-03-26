package jna_impl.RawImplements;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class SCall {
    public interface CLibrary extends Library {
        public static final SCall.CLibrary INSTANCE = (SCall.CLibrary) Native.loadLibrary("cppso", SCall.CLibrary.class);
        int geta();

    }


    int get(){
        return SCall.CLibrary.INSTANCE.geta();
    };


}