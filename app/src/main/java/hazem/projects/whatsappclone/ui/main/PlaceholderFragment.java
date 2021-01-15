package hazem.projects.whatsappclone.ui.main;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hazem.projects.whatsappclone.adapters.CallRecyclerAdapter;
import hazem.projects.whatsappclone.adapters.ChatRecyclerAdapter;
import hazem.projects.whatsappclone.R;
import hazem.projects.whatsappclone.adapters.StatusRecyclerAdapter;
import hazem.projects.whatsappclone.models.Call;
import hazem.projects.whatsappclone.models.Chat;
import hazem.projects.whatsappclone.models.Status;
import hazem.projects.whatsappclone.models.User;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements ActionMode.Callback {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    private ChatRecyclerAdapter mChatRecyclerAdapter;
    private StatusRecyclerAdapter mStatusRecyclerAdapter;
    private CallRecyclerAdapter mCallRecyclerAdapter;
    private List<Chat> mChats = new ArrayList<>();
    private PageViewModel pageViewModel;
    private int mIndex;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        mIndex = 0;
        if (getArguments() != null) {
            mIndex = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(mIndex);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                mChats.add(new Chat(new User("Hazem " + i, "https://scontent.fcai3-1.fna.fbcdn.net/v/t1.0-9/75224847_689238351485265_3897409717034549248_o.jpg?_nc_cat=102&ccb=2&_nc_sid=09cbfe&_nc_eui2=AeHTd16eKwiq0kS6lvPGRVRaKLXY7swlaoUotdjuzCVqhRmEYmVTk1MzbJqwrjg8OACHlDhLKUJJ2mgIHBiCbfD7&_nc_ohc=xTj4yOj0dUgAX_GJmyJ&_nc_ht=scontent.fcai3-1.fna&oh=6405d30c5e0805a49c09e0da5a7feac2&oe=602463C8"),
                        "blablbalbalblablalblablalbalblalblablablalalblalalblablalbalblablalbalablalb"));
            else
                mChats.add(new Chat(new User("Hazem " + i, ""),
                        "blablbalbalblablalblablalbalblalblablablalalblalalblablalbalblablalbalablalb"));
        }
        List<User> users = new ArrayList<>();

        Status status = new Status("", "blablbalbalblablalblablalbalblalblablablalalblalalblablalbalblablalbalablalb",
                "Today, 2:09 AM");
        for (int i = 0; i < 100; i++) {
            User user = new User("Hazem " + i, "https://scontent.fcai3-1.fna.fbcdn.net/v/t1.0-9/75224847_689238351485265_3897409717034549248_o.jpg?_nc_cat=102&ccb=2&_nc_sid=09cbfe&_nc_eui2=AeHTd16eKwiq0kS6lvPGRVRaKLXY7swlaoUotdjuzCVqhRmEYmVTk1MzbJqwrjg8OACHlDhLKUJJ2mgIHBiCbfD7&_nc_ohc=xTj4yOj0dUgAX_GJmyJ&_nc_ht=scontent.fcai3-1.fna&oh=6405d30c5e0805a49c09e0da5a7feac2&oe=602463C8");
            user.setStatuses(new ArrayList<>(Arrays.asList(status, status, status)));
            users.add(user);
        }

        List<Call> calls = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Call call = new Call(new User("Hazem " + i, "https://scontent.fcai3-1.fna.fbcdn.net/v/t1.0-9/75224847_689238351485265_3897409717034549248_o.jpg?_nc_cat=102&ccb=2&_nc_sid=09cbfe&_nc_eui2=AeHTd16eKwiq0kS6lvPGRVRaKLXY7swlaoUotdjuzCVqhRmEYmVTk1MzbJqwrjg8OACHlDhLKUJJ2mgIHBiCbfD7&_nc_ohc=xTj4yOj0dUgAX_GJmyJ&_nc_ht=scontent.fcai3-1.fna&oh=6405d30c5e0805a49c09e0da5a7feac2&oe=602463C8"),
                    "Today, 2:09 AM");
            if (i % 2 == 0)
                call.setCallStatusImg(R.drawable.ic_call_made);
            else call.setCallStatusImg(R.drawable.ic_call_missed);
            calls.add(call);
        }
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mRecyclerView = root.findViewById(R.id.recyclerView_main);
                mChatRecyclerAdapter = new ChatRecyclerAdapter(getActivity(), mChats);
                mStatusRecyclerAdapter = new StatusRecyclerAdapter(users);
                mCallRecyclerAdapter = new CallRecyclerAdapter(calls);


                if (mIndex == 0)
                    mRecyclerView.setAdapter(mChatRecyclerAdapter);
                else if (mIndex == 1)
                    mRecyclerView.setAdapter(mStatusRecyclerAdapter);
                else mRecyclerView.setAdapter(mCallRecyclerAdapter);

                mChatRecyclerAdapter.setFragmentManager(getFragmentManager());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        return root;
    }


    /*
        Action Menu
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}