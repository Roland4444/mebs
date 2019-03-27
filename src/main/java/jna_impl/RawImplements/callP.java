package jna_impl.RawImplements;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class callP {
    public interface CLibrary extends Library {
        public static final callP.CLibrary INSTANCE = (callP.CLibrary) Native.loadLibrary("simple", callP.CLibrary.class);
        void init();
        void call();

    }
    public void init(){
        CLibrary.INSTANCE.init();
    }
    public void call(){
        CLibrary.INSTANCE.call();
    }
}
