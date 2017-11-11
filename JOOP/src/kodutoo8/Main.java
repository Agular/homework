package kodutoo8;

import kodutoo8.textcodec.TextVocalCodec;
import kodutoo8.transmitter.MessageTransmitter;

public class Main {

    public static void main(String[] args){

        MessageTransmitter transmitter = new MessageTransmitter( new TextVocalCodec());

        transmitter.addMessageToQueue("Terekest");
        transmitter.transmitMessages();
    }
}
