package hazem.projects.whatsappclone.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hazem.projects.whatsappclone.ChatViewModel;
import hazem.projects.whatsappclone.R;
import hazem.projects.whatsappclone.dialogs.UserInfoDialog;
import hazem.projects.whatsappclone.models.Chat;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ChatViewHolder> {

    Activity mActivity;
    Context mContext;
    List<Chat> mChats = new ArrayList<>();
    List<Chat> mSelectedChats = new ArrayList<>();

    FragmentManager mFragmentManager;
    private boolean isActionModeEnabled = false;
    private boolean isAllSelected = false;
    private boolean isDeleting = false;
    private ChatViewModel mChatViewModel;


    public ChatRecyclerAdapter(Activity activity, List<Chat> chats) {
        mActivity = activity;
        mChats = chats;
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mChatViewModel = new ViewModelProvider((ViewModelStoreOwner) mContext).get(ChatViewModel.class);
        return new ChatViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.mPosition = position;
        holder.bindViews(mChats.get(position));

        if (isAllSelected)
            holder.handleClickSelect();

    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mUserAvatar;
        public final TextView mUserNameText;
        public final TextView mMessageText;
        public final View mDivider;
        public final CardView mSelectionCheck;
        public int mPosition;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserAvatar = itemView.findViewById(R.id.row_chat_userImage);
            mUserNameText = itemView.findViewById(R.id.row_chat_username);
            mMessageText = itemView.findViewById(R.id.row_chat_message);
            mDivider = itemView.findViewById(R.id.row_chat_divider);
            mSelectionCheck = itemView.findViewById(R.id.row_chat_selection_check);
            setIsRecyclable(true);
            handleOnLongClick();
            itemView.setOnClickListener(v -> handleOnClick());


        }

        private void handleOnClick() {
            if (isActionModeEnabled) {
                handleSelection();
            } else {
                UserInfoDialog dialog = new UserInfoDialog(mChats.get(getAdapterPosition()).getUser());
                dialog.show(getFragmentManager(), "INFO");
            }

        }

        private void handleOnLongClick() {
            itemView.setOnLongClickListener(v -> {

                if (!isActionModeEnabled) {
                    ActionMode.Callback actionCallback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            mode.getMenuInflater().inflate(R.menu.action_bar_menu, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            isActionModeEnabled = true;
                            handleSelection();

                            mChatViewModel.getText().observe((LifecycleOwner) mActivity,
                                    d -> {
                                        if (d == 0) mode.finish();
                                        mode.setTitle(String.format("%d Selected.", d));
                                    });

                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_delete:
                                    mChats.removeAll(mSelectedChats);
                                    mSelectedChats.clear();
                                    notifyDataSetChanged();
                                    mode.finish();
                                    break;

                                case R.id.action_selectAll:

                                    mSelectedChats.clear();
                                    isAllSelected = !isAllSelected;
                                    if (isAllSelected)
                                        mSelectedChats.addAll(mChats);


                                    notifyDataSetChanged();
                                    break;
                            }

                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                            mSelectedChats.clear();
                            isActionModeEnabled = false;
                            isAllSelected = false;
                            notifyDataSetChanged();

                        }
                    };
                    /* */
                    ActionMode mode = ((AppCompatActivity) v.getContext()).startActionMode(actionCallback);
                } else handleSelection();

                return true;
            });
        }

        public void handleClickSelect() {
            itemView.setBackgroundColor(mContext.getResources().getColor(R.color.color_selection));
            mSelectionCheck.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.ZoomIn).duration(100).repeat(0).playOn(mSelectionCheck);
        }

        public void handleClickRemove() {
            itemView.setBackgroundColor(Color.TRANSPARENT);
            mSelectionCheck.setVisibility(View.GONE);
        }

        private void handleSelection() {
            Chat chat = mChats.get(mPosition);
            if (!chat.isSelected()) {
                handleClickSelect();
                chat.setSelected(true);
                mSelectedChats.add(chat);
            } else {
                handleClickRemove();
                chat.setSelected(false);
                mSelectedChats.remove(chat);
            }

            mChatViewModel.setText(mSelectedChats.size());

        }


        public void bindViews(Chat currentChat) {
            if(!currentChat.isSelected()){
              handleClickRemove();
            }
            mUserNameText.setText(currentChat.getUsername());
            mMessageText.setText(currentChat.getMessage());

            if (!currentChat.getUser().getImageUriString().isEmpty())
                Picasso.get().load(currentChat.getUser().getImageUriString()).into(mUserAvatar);
        }


    }
}
