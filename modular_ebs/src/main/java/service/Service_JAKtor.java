package service;

import essens.InputMessage;
import essens.ResponceMessage;
import impl.JAktor;
import jni_impl.RawImplements.callEBS_sound;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Service_JAKtor extends JAktor {
    callEBS_sound cebs = new callEBS_sound();
    String config;
    public void setConfig(String filename){
        this.config=filename;
    }
    @Override
    public void receive(byte[] message) throws IOException {
        var inputMsg = InputMessage.restoreBytesToInputMessage(message);
        var filename = inputMsg.FileName;
        var shortname = new File(filename).getName();
        var ID = inputMsg.ID;
        FileOutputStream fos=new FileOutputStream(shortname);
        fos.write(inputMsg.fileContent);
        fos.close();
        callEBS_sound.CLibrary.ResultCheck rc = new callEBS_sound.CLibrary.ResultCheck();
        rc = cebs.call_ebs("./cv_configuration.json", shortname);
        System.out.print("RESULT OPRATION =>{"+rc.checkResult+","+rc.lastErrorInSession+","+rc.ResultLoadingSoSymbols+"}");
        var resp = new ResponceMessage(rc.checkResult, rc.lastErrorInSession, rc.ResultLoadingSoSymbols, ID);
        System.out.println("SENDING REPLY to"+ inputMsg.Address);
        send(ResponceMessage.saveMessageToBytes(resp), inputMsg.Address);
        System.out.println("SEND complete");





    }

    public static void main(String[] args) throws InterruptedException {
        var  la1=new Service_JAKtor();
        la1.setAddress("http://127.0.0.1:12121/");
        la1.setConfig("./cv_configuration.json");
        la1.spawn();
    }
}
