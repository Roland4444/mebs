package jna_impl.RawImplements;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class callP {
    public interface CLibrary extends Library {
        public static final callP.CLibrary INSTANCE = (callP.CLibrary) Native.loadLibrary("c4", callP.CLibrary.class);
        void initGlobal();
        void call();
        int check(byte[] content, int sizecontent);

    }
    public void init(){
        CLibrary.INSTANCE.initGlobal();
    }
    public void call(){
        CLibrary.INSTANCE.call();
    }
    public  int checkFile(byte[] content){
        return CLibrary.INSTANCE.check(content, content.length);
    }
}
