package jna_impl.RawImplements;


import com.sun.jna.Library;
import com.sun.jna.Native;

public class CallC_BKK {
    public interface CLibrary extends Library {
        public static final CallC_BKK.CLibrary INSTANCE = (CallC_BKK.CLibrary) Native.loadLibrary("uk", CallC_BKK.CLibrary.class);

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
    	 var t = new CallC_BKK();
         t.init();
         System.out.println(t.check("./normal.wav"));
        
    }
}
