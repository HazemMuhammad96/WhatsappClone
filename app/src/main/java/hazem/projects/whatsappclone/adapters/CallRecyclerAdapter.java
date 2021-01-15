package hazem.projects.whatsappclone.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hazem.projects.whatsappclone.R;
import hazem.projects.whatsappclone.models.Call;

public class CallRecyclerAdapter extends RecyclerView.Adapter<CallRecyclerAdapter.CallViewHolder> {

    List<Call> mCalls = new ArrayList<>();

    public CallRecyclerAdapter(List<Call> calls) {
        mCalls = calls;
    }

    @NonNull
    @Override
    public CallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CallViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_call, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CallViewHolder holder, int position) {
        holder.bindViews(mCalls.get(position));
    }

    @Override
    public int getItemCount() {
        return mCalls.size();
    }

    public class CallViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mUserAvatar;
        private final ImageView mCallStatusImage;
        private final TextView mUsernameText;
        private final TextView mCallDate;

        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserAvatar = itemView.findViewById(R.id.row_call_userImage);
            mCallStatusImage = (ImageView) itemView.findViewById(R.id.row_call_callStatus);
            mUsernameText = itemView.findViewById(R.id.row_call_username);
            mCallDate = itemView.findViewById(R.id.row_call_date);
        }

        public void bindViews(Call call){
            Picasso.get().load(call.getCallStatusImg()).placeholder(call.getCallStatusImg()).fit().into(mCallStatusImage);
            Picasso.get().load(Uri.parse(call.getUser().getImageUriString())).into(mUserAvatar);
            mUsernameText.setText(call.getUsername());
            mCallDate.setText(call.getDate());
        }
    }


}
