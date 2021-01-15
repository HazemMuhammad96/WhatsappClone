package hazem.projects.whatsappclone.dialogs;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.squareup.picasso.Picasso;

import hazem.projects.whatsappclone.R;
import hazem.projects.whatsappclone.models.User;

public class UserInfoDialog extends DialogFragment {

    private User mUser;

    public UserInfoDialog(User user) {
        mUser = user;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(700, 830);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = LayoutInflater.from(getContext()).inflate(R.layout.user_info_dialog, null);
        builder.setView(root);
        setRetainInstance(true);

        TextView usernameText = root.findViewById(R.id.dialog_username);
        usernameText.setText(mUser.getUsername());
//        Picasso.get().load(mUser.getImageUriString()).into(userAvatar);
        ImageView userAvatar = root.findViewById(R.id.dialog_avatar);
        if (!mUser.getImageUriString().isEmpty())
        Picasso.get().load(mUser.getImageUriString()).into(userAvatar);

        return builder.create();
    }
}
