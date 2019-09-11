package com.example.bakkingbaker.Adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bakkingbaker.Description;
import com.example.bakkingbaker.OneStepFragment;
import com.example.bakkingbaker.R;
import com.example.bakkingbaker.Room.Step;
import com.example.bakkingbaker.StepFragment;

import java.util.List;


public class StepItemAdapter extends RecyclerView.Adapter<StepItemAdapter.ViewHolder> {

    public static final String STEP_TAG = "stepTag" ;
    public static final String POSITION_TAG = "positionTag" ;
    private List<Step> mStepList;
    private Context mContext;
    public static  boolean mTablet;
    private ViewGroup fragmentContainer;

    public StepItemAdapter(Context context, List<Step> steps) {
        this.mContext = context;
        this.mStepList = steps;
    }

    @Override
    public StepItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.step_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepItemAdapter.ViewHolder holder, final int position) {
        final Step step = mStepList.get(position);
        final Step[] steps = mStepList.toArray(new Step[0]);
        holder.stepShortDescription.setText(step.getShortDescription());
        holder.viewHolderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentContainer = ( (FragmentActivity) mContext).findViewById(R.id.tabDetailFragmentContainer_id);
                mTablet = (fragmentContainer != null);
                Toast.makeText(mContext,"mTablet is"+ mTablet ,Toast.LENGTH_SHORT).show();


                if (mTablet ) {
                    OneStepFragment fragment =  OneStepFragment.newInstance(steps,position);
                    ( (FragmentActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.tabDetailFragmentContainer_id,fragment).commit();
                }
                else {
                    Intent intent = new Intent(mContext, Description.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArray(STEP_TAG, steps);
                    bundle.putInt(POSITION_TAG,position);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View viewHolderView;
        TextView stepShortDescription;


        ViewHolder(View itemView) {
            super(itemView);
            viewHolderView = itemView;
            stepShortDescription = itemView.findViewById(R.id.step_short_description);

        }
    }
}