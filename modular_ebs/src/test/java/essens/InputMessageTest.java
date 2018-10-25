package essens;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputMessageTest {

    @Test
    void saveMessageToBytes() {
        var inpMessage = new InputMessage("000.wav", "00".getBytes(), "voice", "http");
        var restoredBytes = InputMessage.saveMessageToBytes(inpMessage);
        var restoredMsg = InputMessage.restoreBytesToInputMessage(restoredBytes);
        assertEquals(restoredMsg.Address, inpMessage.Address);
        assertEquals(restoredMsg.DescriptionService, inpMessage.DescriptionService);
        assertEquals(restoredMsg.FileName, inpMessage.FileName);
        assertEquals(new String(restoredMsg.fileContent), new String(inpMessage.fileContent));


    }
}