package com.example.bakkingbaker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.CategoryDao;
import com.example.bakkingbaker.Room.Ingredient;
import com.example.bakkingbaker.Room.RoomDB;
import com.example.bakkingbaker.Room.Step;

import java.util.List;

public class Repository {
    private CategoryDao mCategoryDao;
    private LiveData<List<Category>> mCategoryList;
    private LiveData<List<Ingredient>> mIngredientList;
    private LiveData<List<Step>> mStepList;


    public Repository(Application application) {
        RoomDB db = RoomDB.getDatabase(application);
        this.mCategoryDao = db.categoryDao();
//        this.mIngredientDao = db.ingredientDao();
//        this.mStepDao = db.stepDao();
    }

    public LiveData<List<Category>> getAllCategories(){
        return mCategoryList;
    }

    public LiveData<List<Ingredient>> getAllIngredients(){
        return mIngredientList;
    }

    public LiveData<List<Step>> getAllSteps(){
        return mStepList;
    }
}
