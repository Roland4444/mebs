package jni_impl.RawImplements;


import com.sun.jna.Library;
import com.sun.jna.Native;

public class CallCPPBKK {
    public interface CLibrary extends Library {
        public static final CallCPPBKK.CLibrary INSTANCE = (CallCPPBKK.CLibrary) Native.loadLibrary("cpp", CallCPPBKK.CLibrary.class);

        int summ(int a, int b);
        void init();
        int checkFile(String filename);
    }

    public int callsumm(int a, int b){
        return CLibrary.INSTANCE.summ(a,b);
    }

    public void init(){
        CLibrary.INSTANCE.init();
    }

    public int check(String Filename){
        return CLibrary.INSTANCE.checkFile(Filename);
    }
}
