package kodutoo8.transmitter;


import kodutoo8.textcodec.TextCodec;

import java.io.*;

public class MessageTransmitter {

    private String messageQueue = "";
    private TextCodec codec;

    public MessageTransmitter(TextCodec codec) {
        this.codec = codec;
    }

    public void addMessageToQueue(String message) {
        messageQueue = messageQueue + message + ";";
    }

    public void transmitMessages() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String packedMessage = codec.packMessage(messageQueue);
        if (packedMessage != null) {
            try {
                System.out.println(packedMessage);
                writer.write(packedMessage);
                messageQueue = "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
