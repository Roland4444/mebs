package service;


import Message.BKKCheck.InputMessage;
import Message.BKKCheck.ResponceMessage;
import Message.abstractions.BinaryMessage;
import Table.TablesEBSCheck;
import impl.JAktor;
import jni_impl.RawImplements.callEBS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Service_JAKtor extends JAktor {
    TablesEBSCheck tebs = new TablesEBSCheck();
    callEBS checker = new callEBS();
    String configVoice, configPhoto;
    public Service_JAKtor(){
        checker.init();
    }
    public void setconfigVoice(String filename){
        this.configVoice =filename;
    }
    public void setconfigPhoto(String filename){
        this.configPhoto =filename;
    }
    @Override
    public void receive(byte[] message) throws IOException {
        InputMessage inputMsg = (InputMessage) BinaryMessage.restored(message);

        var ID = inputMsg.ID;
        BinaryMessage.write(inputMsg.fileContent, inputMsg.FileName);


        var resp = new ResponceMessage(checker.checkfile(inputMsg.FileName), -1, -1, ID);
        send(BinaryMessage.savedToBLOB(resp), inputMsg.Address);
        new File(inputMsg.FileName).delete();
    }

    public static void main(String[] args) throws InterruptedException {
        var  la1=new Service_JAKtor();
        la1.setAddress("http://127.0.0.1:12128/");
        la1.setconfigVoice("./cv_configuration.json");
        la1.setconfigPhoto("./ci_configuration.json");
        la1.spawn();
    }
}
