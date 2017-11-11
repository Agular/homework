import kodutoo8.textcodec.TextVocalCodec;
import kodutoo8.transmitter.MessageTransmitter;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransmitterAndCodecTests {


    @Test
    public void removesVocalsFromStringNormal() {

        TextVocalCodec codec = new TextVocalCodec();
        String message = "Avastasime uue elevandiliigi, isendid on kollased ja oskavad laulda";
        Assert.assertEquals("vstsm lvndlg, sndd n kllsd j skvd lld", codec.packMessage(message));
    }

    @Test
    public void codecRemovesDoubleSpaces() {
        TextVocalCodec codec = new TextVocalCodec();
        String message = "Tere uuu tere";
        Assert.assertEquals("Tr tr", codec.packMessage(message));
    }

    @Test
    public void codecReceivesMessagesCorrectly() {

        TextVocalCodec codec = mock(TextVocalCodec.class);
        MessageTransmitter transmitter = new MessageTransmitter(codec);
        transmitter.addMessageToQueue("Nägin ahvi!");
        transmitter.addMessageToQueue("Moskiito hammustas kolleegi!");
        transmitter.transmitMessages();
        verify(codec).packMessage("Nägin ahvi!;Moskiito hammustas kolleegi!;");
    }
}
