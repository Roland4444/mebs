package jni_impl.RawImplements;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class TestCall {
    public interface CLibrary extends Library {
        public static final CLibrary INSTANCE = (CLibrary) Native.loadLibrary("ch", CLibrary.class);

        int ret0();

        int ret1();

        int ret_1();

        int ret_3();
    }


    int ret0(){
        return CLibrary.INSTANCE.ret0();
    };

    int ret1(){
        return CLibrary.INSTANCE.ret1();
    };

    int ret_1(){
        return CLibrary.INSTANCE.ret_1();
    }

    int ret_3(){
        return CLibrary.INSTANCE.ret_3();
    };

}
