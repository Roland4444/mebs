package jni_impl.RawImplements;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class callEBS {
    int i = 0;
    public interface CLibrary extends Library {
        public static final CLibrary INSTANCE = (CLibrary) Native.loadLibrary("universal", CLibrary.class);
        public interface void__ extends Callback {
            void apply();
        };

        void__ initGlobal() ;
        int  checkFileGlobal(String filename) ;
    }

    CLibrary.void__ init(){
        return CLibrary.INSTANCE.initGlobal();
    };

    int checkfile(String filename){
        return CLibrary.INSTANCE.checkFileGlobal(filename);
    };

    void foreach(String input) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(input));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(i+++"\t");
            checkfile(line);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        long startTime = System.currentTimeMillis();
        var checker = new callEBS();
        checker.init();
        checker.foreach("input");
        for(int i=0;i<args.length;i++)
            checker.foreach(args[i]);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("total="+elapsedTime/1000+"   Sekunden");
    }


}


