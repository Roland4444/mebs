package jna_impl.RawImplements;


import com.sun.jna.Library;
import com.sun.jna.Native;

public class CallCPPBKK {
    public interface CLibrary extends Library {
        public static final CallCPPBKK.CLibrary INSTANCE = (CallCPPBKK.CLibrary) Native.loadLibrary("uk", CallCPPBKK.CLibrary.class);

        void initGlobal();
        int checkFileGlobal(String filename);
        double getPhotoProblemValueGlobal();
        String getPhotoProblemNameGlobal();
        String sendExampleString();
    }

    public void init(){
        CLibrary.INSTANCE.initGlobal();
    }

    public String getStr(){
        return CLibrary.INSTANCE.sendExampleString();
    }

    public int check(String Filename){
        int i = CLibrary.INSTANCE.checkFileGlobal(Filename);
        System.out.println("RETURNED=>"+i);
        return i;
    }

    public double getProblemPhotoValue(){
        return CLibrary.INSTANCE.getPhotoProblemValueGlobal();
    }

    public String getProblemPhotoName(){
        return CLibrary.INSTANCE.getPhotoProblemNameGlobal();
    }
    
    public static void main(String[] args) {
    	 var t = new CallCPPBKK();
         t.init();
         System.out.println(t.check("./normal.wav"));
        
    }
}
