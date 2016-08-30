package com.inquirly.powershare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import com.inquirly.powershare.R;
import com.inquirly.powershare.activity.InpireMeActivity;
import com.inquirly.powershare.activity.MainActivity;

import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class PowerPostFragment extends Fragment implements View.OnClickListener{

    private View view;
    private Intent intent;
    private static FrameLayout frameLayout;
    private static LinearLayout linearLayout;
    private static FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_power_post, container, false);
        TableRow table_inpireMe = (TableRow) view.findViewById(R.id.table_inpireMe);
        ImageView addVideo = (ImageView) view.findViewById(R.id.addVideoPost);

        table_inpireMe.setOnClickListener(this);
        addVideo.setOnClickListener(this);
        linearLayout = (LinearLayout)view.findViewById(R.id.linear_mainPost);
        frameLayout = (FrameLayout)view.findViewById(R.id.frame_mainPost);
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.table_inpireMe:
                intent = new Intent(getActivity(), InpireMeActivity.class);
                startActivity(intent);
                break;

            case R.id.addVideoPost:
                ((MainActivity)getActivity()).navigateTo(new AddVideoFragment(),"PowerPostFragment",null);
                break;
        }
    }


}
