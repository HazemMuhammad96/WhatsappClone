package hazem.projects.whatsappclone.models;

import java.io.Serializable;

public class Chat implements Serializable {

    private User mUser;
    private String mMessage;

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    private boolean mSelected = false;

    public Chat(User user, String message) {
        mUser = user;
        mMessage = message;
    }


    public String getUsername() {
        return mUser.getUsername();
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
