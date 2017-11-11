package kodutoo8.textcodec;

public class TextVocalCodec implements TextCodec{

    @Override
    public String packMessage(String message) {
        return message.replaceAll("[AEIOUÕÄÖÜaeiouõäöü]", "").replaceAll("  ", " ");
    }
}
