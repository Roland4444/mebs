package jna_impl.RawImplements;
import com.sun.jna.Native;
import com.sun.jna.Library;
public class CallCPPBKK {
    public interface CLibrary extends Library {
        public static final CallCPPBKK.CLibrary INSTANCE = (CallCPPBKK.CLibrary) Native.loadLibrary("uk", CallCPPBKK.CLibrary.class);

        void initGlobal();
        int checkFileGlobal(String filename);
    }

    public void init(){
        CLibrary.INSTANCE.initGlobal();
    }

    public int check(String Filename){
        int i = CLibrary.INSTANCE.checkFileGlobal(Filename);
        System.out.println("RETURNED=>"+i);
        return i;
    }
    
    public static void main(String[] args) {
    	 var t = new CallCPPBKK();
         t.init();
         System.out.println(t.check("./o"));
        
    }
}
