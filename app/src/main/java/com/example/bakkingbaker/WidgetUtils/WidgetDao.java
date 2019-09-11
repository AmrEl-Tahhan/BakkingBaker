package com.example.bakkingbaker.WidgetUtils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.WidgetCategory;

@Dao
public interface WidgetDao {
    @Query("Delete FROM widgetCategory")
    void  deleteAllWidgetCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertWidget( WidgetCategory widget);

    @Query("SELECT * FROM widgetCategory WHERE id = :id")
    WidgetCategory loadWidgetById(String id);

    @Query("SELECT * FROM widgetCategory")
    WidgetCategory loadWidget();
}
