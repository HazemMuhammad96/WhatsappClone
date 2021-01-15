package hazem.projects.whatsappclone.models;

public class Status {
    private String mImageUriString, mDescription, mDate;
    boolean isSeen = false;

    public boolean isSeen() {

        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public Status(String imageUriString, String description, String date) {
        mImageUriString = imageUriString;
        mDescription = description;
        mDate = date;
    }

    public String getImageUriString() {
        return mImageUriString;
    }

    public void setImageUriString(String imageUriString) {
        mImageUriString = imageUriString;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
