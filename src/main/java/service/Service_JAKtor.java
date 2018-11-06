package service;

import essens.InputMessage;
import essens.ResponceMessage;
import essens.TablesEBSCheck;
import impl.JAktor;
import jni_impl.RawImplements.callEBS_sound;
import jni_impl.RawImplements.callebs_photo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Service_JAKtor extends JAktor {
    TablesEBSCheck tebs = new TablesEBSCheck();
    callEBS_sound cebs_v = new callEBS_sound(tebs);
    callebs_photo cebs_i = new callebs_photo(tebs);
    String configVoice, configPhoto;
    public void setconfigVoice(String filename){
        this.configVoice =filename;
    }
    public void setconfigPhoto(String filename){
        this.configPhoto =filename;
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
        if (inputMsg.DescriptionService.equals(tebs.voice)) {
                System.out.println("CHECKING SOUND");
                var rc = cebs_v.call_ebs(configVoice, shortname);
                System.out.print("RESULT OPERATION =>{"+rc.checkResult+","+rc.lastErrorInSession+","+rc.ResultLoadingSoSymbols+"}");
                var resp = new ResponceMessage(rc.checkResult, rc.lastErrorInSession, rc.ResultLoadingSoSymbols, ID);
                System.out.println("SENDING REPLY to"+ inputMsg.Address);
                send(ResponceMessage.saveMessageToBytes(resp), inputMsg.Address);
                System.out.println("SEND complete");
                return;
        };
        if (inputMsg.DescriptionService.equals(tebs.photo)) {
                System.out.println("CHECKING PHOTO");
                var rc = cebs_i.call_ebs_photo(configPhoto, shortname);
                System.out.print("RESULT OPERATION =>{"+rc.checkResult+","+rc.lastErrorInSession+","+rc.ResultLoadingSoSymbols+"}");
                var resp = new ResponceMessage(rc.checkResult, rc.lastErrorInSession, rc.ResultLoadingSoSymbols, ID);
                System.out.println("SENDING REPLY to"+ inputMsg.Address);
                send(ResponceMessage.saveMessageToBytes(resp), inputMsg.Address);
                System.out.println("SEND complete");
                return;
        }

        var resp = new ResponceMessage(-1, -1, -1, ID);
        send(ResponceMessage.saveMessageToBytes(resp), inputMsg.Address);
        System.out.println("Wrong pseudo  => SEND complete");
    }

    public static void main(String[] args) throws InterruptedException {
        var  la1=new Service_JAKtor();
        la1.setAddress("http://127.0.0.1:12121/");
        la1.setconfigVoice("./cv_configuration.json");
        la1.setconfigPhoto("./ci_configuration.json");
        la1.spawn();
    }
}
