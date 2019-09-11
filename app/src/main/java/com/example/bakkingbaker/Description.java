package com.example.bakkingbaker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;
import com.example.bakkingbaker.Adapter.DataItemAdapter;
import com.example.bakkingbaker.Room.Category;
import static com.example.bakkingbaker.Adapter.DataItemAdapter.CATEGORY_TAG;
import static com.example.bakkingbaker.Adapter.StepItemAdapter.POSITION_TAG;
import static com.example.bakkingbaker.Adapter.StepItemAdapter.STEP_TAG;


public class Description extends AppCompatActivity {

    Category mCategory = new Category();
    int position;
    Parcelable[] mStepArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        mCategory = getIntent().getExtras().getParcelable(CATEGORY_TAG);
        if (mCategory != null) {

            Toast.makeText(this, "received " + mCategory.getName(), Toast.LENGTH_SHORT).show();
            boolean test = getIntent().getExtras().getBoolean(DataItemAdapter.INTENT_TAG);


            if (test) {
                initStepFragment(R.id.fragmentContainer);
            } else
                initIngredientFragment(R.id.fragmentContainer);
        } else {

            Bundle extras = getIntent().getExtras();

            mStepArray = extras.getParcelableArray(STEP_TAG);

            position = extras.getInt(POSITION_TAG);
            initOneStepFragment(R.id.fragmentContainer);


        }


    }

    public void initIngredientFragment(int layout) {
        IngredientsFragment fragment = IngredientsFragment.newInstance(mCategory);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, fragment).commit();
    }

    public void initStepFragment(int layout) {
        StepFragment fragment = StepFragment.newInstance(mCategory);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, fragment).commit();
    }

    public void initOneStepFragment(int layout) {
        OneStepFragment fragment = OneStepFragment.newInstance(mStepArray, position);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, fragment).commit();
    }


}
