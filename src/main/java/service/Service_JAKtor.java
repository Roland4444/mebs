package service;
import Message.BKKCheck.InputMessage;
import Message.BKKCheck.ResponceMessage;
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

    @Override
    public void receive(byte[] message) throws IOException {
        InputMessage inputMsg = (InputMessage) BinaryMessage.restored(message);
        var filename = inputMsg.FileName;
        var shortname = new File(filename).getName();
        var ID = inputMsg.ID;
        BinaryMessage.write(inputMsg.fileContent, shortname);
        int res = Bkkwrapper.check(filename);
        var resp = new ResponceMessage(res, ID);
        if ((res != 0) && (shortname.indexOf(".wav")<0))
        {
            System.out.println("Problem founded>>\n");
            String problemParam = Bkkwrapper.getProblemPhotoName();
            Double probValue = Bkkwrapper.getProblemPhotoValue();
            printProblem(problemParam, probValue);
            resp.ProblemName = problemParam;
            resp.problemValue = probValue;
        }
        ResponceMessage.printIt(resp);
        send(BinaryMessage.savedToBLOB(resp), inputMsg.Address);
        //new File(shortname).delete();
    }

    void printProblem(String Name, Double Value){
        System.out.println("Name=" + Name);
        System.out.println("Name=" + Value);
    }

    public static void main(String[] args) throws InterruptedException {
        var  la1=new Service_JAKtor();
        la1.setAddress("http://127.0.0.1:12128/");
        la1.spawn();
    }
}

