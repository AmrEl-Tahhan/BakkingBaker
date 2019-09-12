package com.example.bakkingbaker.WidgetUtils;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.bakkingbaker.MainActivity;
import com.example.bakkingbaker.R;
import com.example.bakkingbaker.Room.Ingredient;
import com.example.bakkingbaker.Room.RoomDB;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class WidgetProvider extends AppWidgetProvider {

    private static final String TAG = "WidgetProvider";
    CharSequence recipeName;
    private RoomDB DB;


    @Override
    public void onUpdate(Context context,
                        final AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


        DB = RoomDB.getDatabase(context);
        for (final int  widgetId : appWidgetIds) {
            Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
            final RemoteViews remoteViews = new RemoteViews(
                    context.getPackageName(), R.layout.widget_layout);
            remoteViews.setOnClickPendingIntent(R.id.widget_ingredients, pendingIntent);



            //on background thread
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {


                    String ingredientsString = "";
                    String id = DB.widgetDao().loadWidget().getId();

                    List<Ingredient> ings = DB.categoryDao().loadCategoryByID(id).getIngredients();

                    int i = 0;
                    for (Ingredient ingredient : ings) {
                        if (ingredientsString != "") {
                            ingredientsString += "\n" + i + ". " + ingredient.getIngredient();

                        } else ingredientsString += i + ". " + ingredient.getIngredient();
                        i++;


                    }
                    remoteViews.setTextViewText(R.id.widget_recipe_name, DB.categoryDao().loadCategoryByID(id).getName());
                    remoteViews.setTextViewText(R.id.widget_ingredients, ingredientsString);
                    appWidgetManager.updateAppWidget(widgetId, remoteViews);
                    Log.i(TAG, "run: " + recipeName);
                }
            });

        }
    }
}




