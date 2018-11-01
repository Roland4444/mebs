package essens;

import java.util.HashMap;
import java.util.Map;

public class TablesEBSCheck {
    public final String voice = "voice";
    public final String photo = "photo";
    public Map<Integer, String> onLoadLibraryErrors;
    public TablesEBSCheck(){
        onLoadLibraryErrors=new HashMap<>();
        onLoadLibraryErrors.put(1, "error loading libcv.so");
        onLoadLibraryErrors.put(2, "error load v_create_session");
        onLoadLibraryErrors.put(3, "error create session");
        onLoadLibraryErrors.put(4, "bullshit in checking file");
        onLoadLibraryErrors.put(5, "error load v_check");
        onLoadLibraryErrors.put(0, "all fine aloaded (So config and checking file)");
    }
}
