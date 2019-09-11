package com.example.bakkingbaker.Room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IngredientTypeClass {
    @TypeConverter
    public static List<Ingredient> stringToIngredients(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {
        }.getType();
        List<Ingredient> ingredients = gson.fromJson(json, type);
        return ingredients;
    }

    @TypeConverter
    public static String ingredientsToString(List<Ingredient> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {
        }.getType();
        String json = gson.toJson(list, type);
        return json;
    }
}

