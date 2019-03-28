package jna_impl.RawImplements;
import com.sun.jna.Native;
import com.sun.jna.Library;
public class CallCPPBKK {
    public interface CLibrary extends Library {
        public static final CallCPPBKK.CLibrary INSTANCE = (CallCPPBKK.CLibrary) Native.loadLibrary("sch", CallCPPBKK.CLibrary.class);

        void init();
        int check(String filename);
    }

    public void init(){
        CLibrary.INSTANCE.init();
    }

    public int check(String Filename){
        return CLibrary.INSTANCE.check(Filename);
    }
    
    public static void main(String[] args) {
    	 var t = new CallCPPBKK();
         t.init();
         System.out.println(t.check("/home/roland/Downloads/lol.wav"));
        
    }
}
