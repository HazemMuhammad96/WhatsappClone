package hazem.projects.whatsappclone.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devlomi.circularstatusview.CircularStatusView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hazem.projects.whatsappclone.R;
import hazem.projects.whatsappclone.models.Status;
import hazem.projects.whatsappclone.models.User;

public class StatusRecyclerAdapter extends RecyclerView.Adapter<StatusRecyclerAdapter.StatusViewHolder> {

    List<User> mStatusUsers = new ArrayList<>();

    public StatusRecyclerAdapter(List<User> statusUsers) {
        mStatusUsers = statusUsers;
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StatusViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_row_status, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        holder.bindViews(mStatusUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mStatusUsers.size();
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mLatestStatusViewer;
        private final CircularStatusView mStatusView;
        private final TextView mUsernameText;
        private final TextView mStatusDate;

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);
            mLatestStatusViewer = itemView.findViewById(R.id.row_status_statusImage);
            mStatusView = itemView.findViewById(R.id.row_status_statusCounter);
            mStatusView.setPortionSpacing(16);
            mUsernameText = itemView.findViewById(R.id.row_status_username);
            mStatusDate = itemView.findViewById(R.id.row_status_date);
        }
        int i = 0;
        public void bindViews(User currentUser) {
//            Picasso.get().load(currentUser.getImageUriString()).into();
            mUsernameText.setText(currentUser.getUsername());
            mStatusDate.setText(currentUser.getLatestStatusDate());
            mStatusView.setPortionsCount(currentUser.getStatuses().size());
            itemView.setOnClickListener(v -> {
                if (i >= currentUser.getStatuses().size())
                    return;

                Status status = currentUser.getStatuses().get(i);
                if (!status.isSeen()) {
                    mStatusView.setPortionColorForIndex(i, Color.LTGRAY);
                    i++;
                }
            });
        }
    }
}
