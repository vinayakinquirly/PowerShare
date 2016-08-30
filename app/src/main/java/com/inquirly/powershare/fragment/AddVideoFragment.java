package com.inquirly.powershare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inquirly.powershare.R;
import com.inquirly.powershare.activity.MainActivity;

public class AddVideoFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_video, container, false);
        ((MainActivity)getActivity()).configureToolBar("Add Video","basic",true);

        MainActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).navigateTo(new MainPowerShareFragment(),"MainPowerShareFragment",null);
            }
        });
        return view;
    }
}