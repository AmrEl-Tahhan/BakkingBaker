package com.example.bakkingbaker;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {


     public static final String TAG = "aaaaa";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");






    }

    private void commitFragment() {
        MainListFragment mainFragment = new MainListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment_id, mainFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        commitFragment();
    }
}
