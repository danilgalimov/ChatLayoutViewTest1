package name.danilgalimov.chattest;

public class Contact {

    private int mContactId;
    private String mDisplayName;
    private String mPhoneNumber;
    private int mAvatar;

    public Contact(int contactId, String displayName, String phoneNumber, int avatar) {
        mContactId = contactId;
        mDisplayName = displayName;
        mPhoneNumber = phoneNumber;
        mAvatar = avatar;
    }

    public int getContactId() {
        return mContactId;
    }

    public void setContactId(int contactId) {
        this.mContactId = contactId;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        this.mAvatar = avatar;
    }
}
