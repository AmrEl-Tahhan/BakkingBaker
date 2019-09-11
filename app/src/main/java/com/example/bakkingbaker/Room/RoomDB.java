package com.example.bakkingbaker.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bakkingbaker.WidgetUtils.WidgetDao;

@Database(entities = {Category.class,Ingredient.class,Step.class,WidgetCategory.class},version = 1,exportSchema = false)


public abstract class RoomDB extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract WidgetDao widgetDao();
//    public abstract IngredientDao ingredientDao();
//    public abstract StepDao stepDao();

    private static  RoomDB INSTANCE = null;

    public static RoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "baking_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
