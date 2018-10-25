package service;

import impl.JAktor;

import java.io.FileOutputStream;
import java.io.IOException;

public class Service_JAKtor extends JAktor {
    @Override
    public void receive(byte[] message) throws IOException {
        this.received=new String(message);
        FileOutputStream fos=new FileOutputStream("RECZ");
        fos.write(message);
        fos.close();
    }
}
