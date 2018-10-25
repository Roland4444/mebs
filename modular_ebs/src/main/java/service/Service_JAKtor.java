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
        FileOutputStream fos=new FileOutputStream(new File(filename).getName());
        fos.write(inputMsg.fileContent);
        fos.close();
        var result = cebs.call_ebs(this.config, shortname);
        var resp = new ResponceMessage(result.checkResult, result.lastErrorInSession, result.ResultLoadingSoSymbols);
        send(ResponceMessage.saveMessageToBytes(resp), inputMsg.Address);
    }
}
