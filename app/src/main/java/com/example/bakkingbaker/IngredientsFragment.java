package com.example.bakkingbaker;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bakkingbaker.Adapter.DataItemAdapter;
import com.example.bakkingbaker.Adapter.IngredientItemAdapter;
import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.Ingredient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


import static com.example.bakkingbaker.Adapter.DataItemAdapter.CATEGORY_TAG;
import static com.example.bakkingbaker.Adapter.DataItemAdapter.mTablet;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientsFragment extends Fragment {
    private static final String INTENT_TAG = "steps?" ;
    private IngredientItemAdapter adapter;
    private RecyclerView rv;
    private FloatingActionButton mFAB;
    private Category mCategory ;
    private List<Ingredient> mIngredientList;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    public static IngredientsFragment newInstance(Category mCategory) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putParcelable(CATEGORY_TAG, mCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ingredients, container, false);
        rv =rootView.findViewById(R.id.ingredient_rv);
        assert getArguments() != null;
        mCategory = getArguments().getParcelable(CATEGORY_TAG);
        assert mCategory != null;
        mIngredientList = mCategory.getIngredients();
        initRecyclerView();
        mFAB = rootView.findViewById(R.id.fab_id);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mTablet) {
                    StepFragment mStepFragment = StepFragment.newInstance(mCategory);
                    getActivity().getSupportFragmentManager().
                            beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.mainFragment_id,mStepFragment).commit();
                }
                else {
                    Intent intent = new Intent(getContext(), Description.class);
                    intent.putExtra(CATEGORY_TAG, mCategory);
                    intent.putExtra(INTENT_TAG,true);
                    startActivity(intent);
                }
                ///////////////////////////////////////////////////////////////////


            }
        });

        return rootView;
    }

    private void initRecyclerView() {
        adapter = new IngredientItemAdapter(getContext(), mIngredientList);
        rv.setAdapter(adapter);

    }

}
