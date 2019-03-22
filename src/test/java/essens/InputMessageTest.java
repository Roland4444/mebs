package essens;

import Message.BKKCheck.InputMessage;
import Message.abstractions.BinaryMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputMessageTest {

    @Test
    void saveMessageToBytes() {
        var inpMessage = new InputMessage("000.wav", "00".getBytes(), "voice", "http", "00");
        var restoredBytes = BinaryMessage.savedToBLOB(inpMessage);
        InputMessage restoredMsg = (InputMessage) BinaryMessage.restored(restoredBytes);
        assertEquals(restoredMsg.Address, inpMessage.Address);
        assertEquals(restoredMsg.FileName, inpMessage.FileName);
        assertEquals(new String(restoredMsg.fileContent), new String(inpMessage.fileContent));


    }
}