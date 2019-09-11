package com.example.bakkingbaker.Room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import androidx.room.TypeConverters;


import com.google.gson.annotations.Expose;
@TypeConverters({IngredientTypeClass.class, StepTypeClass.class})
@Entity(tableName = "widgetCategory")
public class WidgetCategory  {
    @PrimaryKey
    @Expose
    @NonNull
    private String id;
    @Ignore
    public WidgetCategory(String id) {
        this.id = id;
    }

    public WidgetCategory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}