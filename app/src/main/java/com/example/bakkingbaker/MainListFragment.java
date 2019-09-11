package com.example.bakkingbaker;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakkingbaker.Adapter.DataItemAdapter;
import com.example.bakkingbaker.RetrofitAPI.WebService;
import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.CategoryDao;
import com.example.bakkingbaker.Room.RoomDB;
import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainListFragment extends Fragment {

    private Category[] categories;
    private List<Category> cats = new ArrayList<>();
    CharSequence recipeName;
    CharSequence ingredients;
    Category cat;
    private List<Category> mCategoryList = new ArrayList<>();
    private RecyclerView rv;
    private RoomDB DB ;
    private CategoryDao categoryDao ;
    public static final String TAG ="tag";
    public Executor executor = Executors.newSingleThreadExecutor();


    public MainListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main_list, container, false);
        rv = view.findViewById(R.id.recyclerView_id);
        requestData();
        Log.i(TAG, "onCreateView: ");






        return view;
    }

    @Override
    public void onResume() {
                executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    public void requestData(){
        WebService webService = WebService.retrofit.create(WebService.class);
        Call<Category[]> call = webService.categories();
        call.enqueue(new Callback<Category[]>() {
            @Override
            public void onResponse(Call<Category[]> call,final Response<Category[]> response) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        categories = response.body();

                        //  Toast.makeText(getActivity(), "received "+categories.length+" from server",Toast.LENGTH_SHORT).show();
                        mCategoryList = Arrays.asList(categories);
                        DB = RoomDB.getDatabase(getActivity());
                        categoryDao = DB.categoryDao();
                        categoryDao.insertAllCategories(mCategoryList);
                        cats = DB.categoryDao().getAllCategories();
                        cat = DB.categoryDao().loadCategoryByID("1");
                        recipeName = cat.getName();
                        Looper.prepare();
                        Log.i(TAG, "run: "+ cats);
                        Log.i(TAG, "run: "+ cat);
                        Log.i(TAG, "run: "+ categoryDao.getAllCategories());

                    }
                });

                initRecyclerView();
            }

            @Override
            public void onFailure(Call<Category[]> call, Throwable t) {

            }
        });


    }

    private void initRecyclerView(){
        DataItemAdapter adapter = new DataItemAdapter(getContext(), mCategoryList);
        rv.setAdapter(adapter);

    }

}
