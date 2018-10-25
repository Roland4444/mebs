package essens;

import java.io.*;

public class InputMessage implements Serializable {
    public InputMessage(String FileName, byte[] fileContent, String DescriptionService, String Address) {
        this.Address=Address;
        this.FileName=FileName;
        this.fileContent=fileContent;
        this.DescriptionService=DescriptionService;
    }
    public String FileName ;
    public byte[] fileContent;
    public String DescriptionService;
    public String Address;

    public static  byte[] saveMessageToBytes(InputMessage inp){
            byte[] Result=null ;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = null;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(inp);
                out.flush();
                Result = bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {

                }
            }
            return Result;
    }

    public static InputMessage restoreBytesToInputMessage(byte[] input){
            Object o=null;
            ByteArrayInputStream bis = new ByteArrayInputStream(input);
            ObjectInput in = null;
            try {
                in = new ObjectInputStream(bis);
                o = in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {

                }
            }
            return (InputMessage) o;
    }

}


