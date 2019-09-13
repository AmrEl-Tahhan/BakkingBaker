package com.example.bakkingbaker;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bakkingbaker.Adapter.StepItemAdapter;
import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.Step;
import java.util.List;

import static com.example.bakkingbaker.Adapter.DataItemAdapter.CATEGORY_TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment {



    private StepItemAdapter adapter;
    ViewGroup view;
    private RecyclerView rv;
    private List<Step> mStepList ;

    public StepFragment() {
        // Required empty public constructor
    }

    public static StepFragment newInstance(Category mCategory) {
        StepFragment fragment = new StepFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_step, container, false);
        rv =rootView.findViewById(R.id.step_rv);
        Category mCategory ;
        mCategory = getArguments().getParcelable(CATEGORY_TAG);
        mStepList = mCategory.getSteps();
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        adapter = new StepItemAdapter(getContext(), mStepList);
        rv.setAdapter(adapter);

    }
}
