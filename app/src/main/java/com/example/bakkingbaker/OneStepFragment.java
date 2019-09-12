package com.example.bakkingbaker;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.bakkingbaker.Room.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static com.example.bakkingbaker.Adapter.StepItemAdapter.POSITION_TAG;
import static com.example.bakkingbaker.Adapter.StepItemAdapter.STEP_TAG;
import static com.example.bakkingbaker.Adapter.StepItemAdapter.mTablet;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneStepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneStepFragment extends Fragment {

    private TextView shortDescription;
    private TextView description;
    private TextView previousTV;
    private TextView positionTV;
    private TextView nextTV;

    private SimpleExoPlayer mExoPlayer;
    private PlayerView mPlayerView;



    public OneStepFragment() {
        // Required empty public constructor
    }


    public static OneStepFragment newInstance(Parcelable[] mStep, int position) {
        OneStepFragment fragment = new OneStepFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(STEP_TAG, mStep);
        args.putInt(POSITION_TAG,position);
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_one_step, container, false);
        final Parcelable[] mStepArray =  getArguments().getParcelableArray(STEP_TAG);
        final int mPosition = getArguments().getInt(POSITION_TAG);
        final Step mStep = (Step) mStepArray[mPosition];
        mPlayerView = rootView.findViewById(R.id.videoView);
        shortDescription = rootView.findViewById(R.id.shortDescription);


        assert mStep != null;
        shortDescription.setText(mStep.getShortDescription());
        description = rootView.findViewById(R.id.Description);
        description.setText(mStep.getDescription());
        previousTV = rootView.findViewById(R.id.navigation_previous);
        positionTV = rootView.findViewById(R.id.navigation_position);
        nextTV = rootView.findViewById(R.id.navigation_next);


        //initialize player
        if (!mStep.getVideoURL().equals("") ){
            if (mTablet){
                initializePlayer(Uri.parse(mStep.getVideoURL()));}
            else  {
                if (getResources().getBoolean(R.bool.isLandscapePhone)) {

                    mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    initializePlayer(Uri.parse(mStep.getVideoURL()));

                }

                else initializePlayer(Uri.parse(mStep.getVideoURL()));
            }
        }
        else {

            mPlayerView.setVisibility(View.GONE);

        }

        nextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = mPosition;
                position++;
                initFragmentTransaction(mStepArray,position);

            }
        });

        previousTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = mPosition;
                position--;
                initFragmentTransaction(mStepArray,position);

            }
        });

        positionTV.setText(mPosition +" / "+mStepArray.length);



        return rootView;
    }

    private void initializePlayer(Uri mediaUri) {

        if (mExoPlayer == null) {
            // create an instance of exoplayer
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            //prepare the media
            String userAgent = Util.getUserAgent(getActivity(), "BakkingBaker");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(getActivity(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);

        }



    }

    private void initFragmentTransaction(Parcelable[] mStepArray,int position){
        OneStepFragment fragment =  OneStepFragment.newInstance(mStepArray,position);
        if (mTablet ) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.tabDetailFragmentContainer_id,fragment).commit();

    }
        else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer,fragment).commit();

        }

    }
}
