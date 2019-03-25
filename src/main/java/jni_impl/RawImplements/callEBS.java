package jni_impl.RawImplements;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class callEBS {

    public interface CLibrary extends Library {
        public static final CLibrary INSTANCE = (CLibrary) Native.loadLibrary("nosess", CLibrary.class);
        public interface void__ extends Callback {
            void apply();
        };

        void__ initGlobal() ;
        int  checkFileGlobal(String filename) ;
        void foreachGlobal(String filename);
        int lets_check(String filename);
    }

    public int lets_check(String f){
       return CLibrary.INSTANCE.lets_check(f);
    };

    public void callForeach(String f){
        CLibrary.INSTANCE.foreachGlobal(f);
    };

    public void init(){
        CLibrary.INSTANCE.initGlobal();
    };

    public int checkfile(String filename){
        return CLibrary.INSTANCE.checkFileGlobal(filename);
    };

    public void foreach(String input) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(input));
        String line;
        while ((line = br.readLine()) != null) {

            checkfile(line);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        long startTime = System.currentTimeMillis();
        var checker = new callEBS();
        checker.init();
        checker.callForeach("wav");
       // checker.foreach("wav");
      //  for(int i=0;i<args.length;i++)
       //     checker.foreach(args[i]);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("total="+elapsedTime/1000+"   Sekunden");
    }


}


