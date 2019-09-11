package com.example.bakkingbaker.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("Select * FROM category")
    List<Category> getAllCategories();

    @Insert
    void  insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertAllCategories(List<Category> categoryList);


    @Query("SELECT * FROM category WHERE name = :name")
    Category loadCategoryByName(String name);

    @Query("SELECT * FROM category WHERE id = :id")
    Category loadCategoryByID(String id);


}
