package com.example.bakkingbaker.Room;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StepTypeClass {
    @TypeConverter
    public static List<Step> stringToSteps(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Step>>() {
        }.getType();
        List<Step> steps = gson.fromJson(json, type);
        return steps;
    }

    @TypeConverter
    public static String stepsToString(List<Step> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Step>>() {
        }.getType();
        String json = gson.toJson(list, type);
        return json;
    }
}