package essens;

import java.io.*;

public class ResponceMessage implements Serializable{
    public ResponceMessage(int checkResult,  int lastErrorInSession, int ResultLoadingSoSymbols, String ID){
        this.checkResult=checkResult;
        this.lastErrorInSession=lastErrorInSession;
        this.ResultLoadingSoSymbols=ResultLoadingSoSymbols;
        this.ID=ID;
    }
    public int checkResult;
    public int lastErrorInSession;
    public int ResultLoadingSoSymbols;
    public String ID;

    public static  byte[] saveMessageToBytes(ResponceMessage inp){
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

    public static ResponceMessage restoreBytesToResponceMessage(byte[] input){
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
        return (ResponceMessage) o;
    }
}
