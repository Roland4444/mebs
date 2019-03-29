package jna_impl.RawImplements;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class callP {
    public interface CLibrary extends Library {
        public static final callP.CLibrary INSTANCE = (callP.CLibrary) Native.loadLibrary("simple", callP.CLibrary.class);
        void init();
        void call();
        int checkFile(byte[] content, long sizecontent);
        int lets_check(String filename);

    }
    public void init(){
        CLibrary.INSTANCE.init();
    }
    public void call(){
        CLibrary.INSTANCE.call();
    }
    public  int lets_check(byte[] content, long sizecontent){
        return CLibrary.INSTANCE.checkFile(content, content.length);
    }
    public  int checkFile(byte[] content, long sizecontent){
        return CLibrary.INSTANCE.checkFile(content, content.length);
    }
}
