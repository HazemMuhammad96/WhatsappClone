package hazem.projects.whatsappclone.models;

public class Call {

    private User mUser;
    private String mDate;
    private int mCallStatusImg;

    public Call(User user, String date) {
        mUser = user;
        mDate = date;
    }


    public int getCallStatusImg() {
        return mCallStatusImg;
    }

    public void setCallStatusImg(int callStatusImg) {
        mCallStatusImg = callStatusImg;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getUsername() {
        return mUser.getUsername();
    }

}
