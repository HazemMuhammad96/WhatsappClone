package hazem.projects.whatsappclone.models;

import java.util.List;

public class User {
    private String mUsername, mImageUriString;
    private List<Status> mStatuses;

    public User(String username, String imageUriString) {
        mUsername = username;
        mImageUriString = imageUriString;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getImageUriString() {
        return mImageUriString;
    }

    public void setImageUriString(String imageUriString) {
        mImageUriString = imageUriString;
    }

    public List<Status> getStatuses() {
        return mStatuses;
    }

    public void setStatuses(List<Status> statuses) {
        mStatuses = statuses;
    }

    public String getLatestStatusImage() {
        return mStatuses.get(mStatuses.size() - 1).getImageUriString();
    }

    public String getLatestStatusDate() {
        return mStatuses.get(mStatuses.size() - 1).getDate();
    }
}

