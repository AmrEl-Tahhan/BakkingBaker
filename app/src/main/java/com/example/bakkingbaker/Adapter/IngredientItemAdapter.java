package com.example.bakkingbaker.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bakkingbaker.R;
import com.example.bakkingbaker.Room.Ingredient;
import java.util.List;

public class IngredientItemAdapter extends RecyclerView.Adapter<IngredientItemAdapter.ViewHolder> {

    private List<Ingredient> mIngredientList ;
    private Context mContext;


    public IngredientItemAdapter(Context context, List<Ingredient> ingredients) {
        this.mContext = context;
        this.mIngredientList = ingredients;
    }

    @Override
    public IngredientItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.ingredient_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientItemAdapter.ViewHolder holder, int position) {
           Ingredient Ingredient = mIngredientList.get(position);
           holder.ingredientName.setText(Ingredient.getIngredient());
           holder.ingredientMeasure.setText(Ingredient.getMeasure());
           holder.IngredientQuantity.setText(String.valueOf(Ingredient.getQuantity()));



    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
View viewHolderView;
        public TextView ingredientName;
        public TextView ingredientMeasure;
        public TextView IngredientQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            viewHolderView = itemView;
            ingredientName =  itemView.findViewById(R.id.ingredientTextView_id);
            ingredientMeasure =  itemView.findViewById(R.id.measureTextView_id);
            IngredientQuantity =  itemView.findViewById(R.id.quantityTextView_id);
        }
    }
}