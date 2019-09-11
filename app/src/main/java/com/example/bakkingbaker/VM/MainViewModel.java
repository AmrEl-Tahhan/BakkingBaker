package com.example.bakkingbaker.VM;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.bakkingbaker.Repository;
import com.example.bakkingbaker.Room.Category;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository mRepository;
    private LiveData<List<Category>> mCategoryList;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
        this.mCategoryList = mRepository.getAllCategories();
    }
    // This completely hides the implementation from the UI.
    LiveData<List<Category>> getAllCategories(){
        return mCategoryList;
    }
}
