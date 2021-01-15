package hazem.projects.whatsappclone;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewModel extends ViewModel {

    MutableLiveData<Integer> mMutableLiveData = new MutableLiveData<>();

    public void setText(int value){
        mMutableLiveData.setValue(value);
    }

    public MutableLiveData<Integer> getText(){
        return mMutableLiveData;
    }
}
