package service;
import Message.BKKCheck.InputMessage;
import Message.abstractions.BinaryMessage;
import Table.TablesEBSCheck;
import impl.JAktor;
import jna_impl.RawImplements.CallC_BKK;
import java.io.File;
import java.io.IOException;

public class Service_JAKtor extends JAktor {
    TablesEBSCheck tebs = new TablesEBSCheck();
    private CallC_BKK Bkkwrapper;
    public Service_JAKtor(){
        this.Bkkwrapper = new CallC_BKK();
        Bkkwrapper.init();
    };

    String configVoice, configPhoto;
    public void setconfigVoice(String filename){
        this.configVoice =filename;
    }
    public void setconfigPhoto(String filename){
        this.configPhoto =filename;
    }
    @Override
    public void receive(byte[] message) throws IOException {
        InputMessage inputMsg = (InputMessage) BinaryMessage.restored(message);
        var filename = inputMsg.FileName;
        var shortname = new File(filename).getName();
        var ID = inputMsg.ID;
        BinaryMessage.write(inputMsg.fileContent, shortname);
      //////////////////  var resp = new ResponceMessage(Bkkwrapper.check(filename), ID);
        send(BinaryMessage.savedToBLOB(null), inputMsg.Address);
        //new File(shortname).delete();
    }

    public static void main(String[] args) throws InterruptedException {
        var  la1=new Service_JAKtor();
        la1.setAddress("http://127.0.0.1:12128/");
        la1.setconfigVoice("./cv_configuration.json");
        la1.setconfigPhoto("./ci_configuration.json");
        la1.spawn();
    }
}

