package name.danilgalimov.chattest;

public class Message {

    private String mSender;
    private String mText;

    public Message(String sender, String text) {
        mSender = sender;
        mText = text;
    }

    public String getSender() {
        return mSender;
    }

    public String getText() {
        return mText;
    }
}
