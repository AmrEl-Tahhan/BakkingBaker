package com.example.bakkingbaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakkingbaker.IngredientsFragment;
import com.example.bakkingbaker.Description;
import com.example.bakkingbaker.R;
import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.RoomDB;
import com.example.bakkingbaker.Room.WidgetCategory;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    public static final String INTENT_TAG = "steps?";
    private List<Category> mCategories;
    private Context mContext;
    public static boolean mTablet;
    private ViewGroup fragmentContainer;
    public static final String CATEGORY_TAG = "categoryItem";

    public void update(List<Category> categories){
        this.mCategories = categories ;
        notifyDataSetChanged();
    }


    public DataItemAdapter(Context context, List<Category> categories) {
        this.mContext = context;
        this.mCategories = categories;
    }

    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final Category category = mCategories.get(position);
        holder.recipeName.setText(category.getName());
        holder.addWidgetTextView.setText(R.string.set_widget);

        holder.recipeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentContainer = ((FragmentActivity) mContext).findViewById(R.id.tabDetailFragmentContainer_id);
                mTablet = (fragmentContainer != null);
                Toast.makeText(mContext, "mTablet is" + mTablet, Toast.LENGTH_SHORT).show();


                if (mTablet) {
                    IngredientsFragment fragment = IngredientsFragment.newInstance(category);
                    ((FragmentActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.tabDetailFragmentContainer_id, fragment).commit();
                } else {
                    Intent intent = new Intent(mContext, Description.class);
                    intent.putExtra(CATEGORY_TAG, category);
                    intent.putExtra(INTENT_TAG, false);
                    mContext.startActivity(intent);
                }


            }
        });

        holder.addWidgetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        RoomDB db = RoomDB.getDatabase(mContext);
                        db.widgetDao().deleteAllWidgetCategories();
                        Log.i("check if empety", "run: "+db.widgetDao().loadWidget());
                        WidgetCategory widgetCategory = new WidgetCategory();
                        widgetCategory.setId(String.valueOf(category.getId()));
                        db.widgetDao().insertWidget(widgetCategory);
                      //  Looper.prepare();
                    //
                        Log.i("widget id is ", "run: "+db.widgetDao().loadWidget());
                    }
                });
                Toast.makeText(mContext,mContext.getString(R.string.updated),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View viewHolderView;
        TextView recipeName;
        TextView addWidgetTextView;

        ViewHolder(View itemView) {
            super(itemView);
            viewHolderView = itemView;
            recipeName = itemView.findViewById(R.id.recipe_textView);
            addWidgetTextView = itemView.findViewById(R.id.setWidgetItemID);
        }
    }
}